package amerifrance.concoctions.tile;

import amerifrance.concoctions.CreativeConcoctions;
import amerifrance.concoctions.api.CreativeConcoctionsAPI;
import amerifrance.concoctions.api.MetaBlock;
import amerifrance.concoctions.api.cauldron.HeatSource;
import amerifrance.concoctions.api.cauldron.ICauldron;
import amerifrance.concoctions.api.cauldron.IHeatController;
import amerifrance.concoctions.api.concoctions.Concoction;
import amerifrance.concoctions.api.ingredients.Ingredient;
import amerifrance.concoctions.api.ingredients.IngredientProperties;
import amerifrance.concoctions.api.registry.ConcoctionRecipes;
import amerifrance.concoctions.api.registry.HeatSourceRegistry;
import amerifrance.concoctions.registry.ItemsRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;
import java.util.List;

public abstract class TileCauldronBase extends TileEntity implements ICauldron {

    private int ingredientCapacity;
    private float heatCapacity;
    private float maxUnstability;

    public ArrayList<IngredientProperties> cauldronContent;
    public float unstability;
    public int potency;
    public float heat;
    public int ticksLeft;

    public TileCauldronBase(int ingredientCapacity, float heatCapacity, float maxUnstability) {
        this.ingredientCapacity = ingredientCapacity;
        this.heatCapacity = heatCapacity;
        this.maxUnstability = maxUnstability;

        this.cauldronContent = new ArrayList<IngredientProperties>();
        this.unstability = 0F;
        this.heat = 0F;
        this.potency = 0;
        this.ticksLeft = 0;
    }

    @Override
    public int getIngredientCapacity() {
        return ingredientCapacity;
    }

    @Override
    public float getHeatCapacity() {
        return heatCapacity;
    }

    @Override
    public float getMaxUnstability() {
        return maxUnstability;
    }

    @Override
    public float getHeat() {
        return heat;
    }

    @Override
    public float getUnstability() {
        return unstability;
    }

    @Override
    public boolean checkRecipe() {
        if (!cauldronContent.isEmpty())
            return ConcoctionRecipes.getConcoctionForIngredients(cauldronContent) != null;
        return false;
    }

    @Override
    public boolean canCraft() {
        return ticksLeft == 0;
    }

    @Override
    public void addIngredient(Ingredient ingredient, int stacksize) {
        ticksLeft += ingredient.ticksToBoil * stacksize;
        unstability += ingredient.unstability * stacksize;
        potency += ingredient.potency * stacksize;
        List<IngredientProperties> propertiesList = new ArrayList<IngredientProperties>(ingredient.getPropertiesList());

        if (propertiesList.contains(IngredientProperties.CATALYST)) {
            ticksLeft -= 5 * stacksize * ingredient.potency * ingredient.ticksToBoil / 100;
            propertiesList.remove(IngredientProperties.CATALYST);
        }
        if (propertiesList.contains(IngredientProperties.UNSTABLE)) {
            unstability += 5 * stacksize * ingredient.potency * ingredient.unstability / 100;
            propertiesList.remove(IngredientProperties.UNSTABLE);
        }
        if (propertiesList.contains(IngredientProperties.STABILIZER)) {
            unstability -= 5 * stacksize * ingredient.potency * ingredient.unstability / 100;
            propertiesList.remove(IngredientProperties.STABILIZER);
        }
        if (propertiesList.contains(IngredientProperties.COOLANT)) {
            heat -= 5 * stacksize * ingredient.potency / 100;
            propertiesList.remove(IngredientProperties.COOLANT);
        }

        for (int i = 0; i < stacksize; i++) cauldronContent.addAll(propertiesList);
        if (worldObj.isRemote)
            CreativeConcoctions.proxy.cauldronSplash(worldObj, xCoord, yCoord + 0.8, zCoord, stacksize);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        setToZero();
        handleHeat();

        if (ticksLeft > 0) ticksLeft--;
        if (worldObj.getTotalWorldTime() % 40 == 0) markForUpdate();

        if (getHeat() > getHeatCapacity()) meltCauldron();
        if (cauldronContent.size() > getIngredientCapacity()) cauldronOverflow();
        if (getUnstability() > getMaxUnstability()) cauldronUnstable();

        if (ticksLeft == 0 && !cauldronContent.isEmpty()) {
            if (worldObj.isRemote)
                CreativeConcoctions.proxy.cauldronFumes(worldObj, xCoord, yCoord + getLiquidHeightForRender(), zCoord);
        }
    }

    @Override
    public void handleHeat() {
        if (!worldObj.isAirBlock(xCoord, yCoord - 1, zCoord)) {
            Block block = worldObj.getBlock(xCoord, yCoord - 1, zCoord);
            int metadata = worldObj.getBlockMetadata(xCoord, yCoord - 1, zCoord);
            MetaBlock metaBlock = new MetaBlock(block, metadata);
            TileEntity tile = worldObj.getTileEntity(xCoord, yCoord - 1, zCoord);

            if (HeatSourceRegistry.contains(metaBlock)) {
                HeatSource heatSource = HeatSourceRegistry.getHeatSource(metaBlock);
                if (worldObj.getTotalWorldTime() % heatSource.ticksToWait == 0 && getHeat() < heatSource.maxHeat) {
                    heat += 0.1;
                }
            } else if (tile != null && tile instanceof IHeatController) {
                IHeatController heatController = (IHeatController) tile;
                heatController.handleCauldronHeat(this, worldObj, xCoord, yCoord, zCoord);
            } else if (worldObj.getTotalWorldTime() % 70 == 0 && getHeat() > 0.3) {
                heat -= 0.3;
            }
        }
    }

    @Override
    public float getLiquidHeightForRender() {
        float height = 0.25F + 0.75F * ((float) cauldronContent.size() / getIngredientCapacity());
        if (height > 1.0F) height = 1.0F;
        return height;
    }

    public boolean checkAndCraft(EntityPlayer player, ItemStack heldItem) {
        if (heldItem != null && heldItem.getItem() == Items.glass_bottle && heldItem.stackSize > 0 && canCraft()) {
            ItemStack concoctionStack = new ItemStack(ItemsRegistry.concoctionItem);
            if (checkRecipe()) {
                Concoction concoction = ConcoctionRecipes.getConcoctionForIngredients(cauldronContent);
                int level = potency / cauldronContent.size();
                int duration = (int) (level * getHeat() / CreativeConcoctionsAPI.dividingSafeInt((int) getUnstability())) * 100;

                if (level < 1) level = 1;
                if (level > concoction.maxLevel) level = concoction.maxLevel;
                ItemsRegistry.concoctionItem.setConcoction(concoctionStack, concoction);
                ItemsRegistry.concoctionItem.setIngredientPotency(concoctionStack, level);
                ItemsRegistry.concoctionItem.setDuration(concoctionStack, duration);

                cauldronContent.clear();
                potency = 0;
                unstability = 0;

                heldItem.stackSize--;
                player.inventory.addItemStackToInventory(concoctionStack.copy());
                player.inventoryContainer.detectAndSendChanges();
                return true;
            } else {
                invalidRecipe();
                return false;
            }
        }
        return false;
    }

    public void markForUpdate() {
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    public void setToZero() {
        if (ticksLeft < 0) ticksLeft = 0;
        if (heat < 0) heat = 0;
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        this.unstability = tagCompound.getFloat("unstability");
        this.potency = tagCompound.getInteger("potency");
        this.heat = tagCompound.getFloat("heat");
        this.ticksLeft = tagCompound.getInteger("ticksLeft");

        heatCapacity = tagCompound.getFloat("heatCapacity");
        ingredientCapacity = tagCompound.getInteger("ingredientCapacity");
        maxUnstability = tagCompound.getFloat("maxUnstability");

        NBTTagList tagList = tagCompound.getTagList("ingredients", Constants.NBT.TAG_STRING);
        if (tagList != null) {
            cauldronContent.clear();
            for (int i = 0; i < tagList.tagCount(); i++) {
                cauldronContent.add(IngredientProperties.valueOf(tagList.getStringTagAt(i)));
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        tagCompound.setFloat("unstability", unstability);
        tagCompound.setInteger("potency", potency);
        tagCompound.setFloat("heat", heat);
        tagCompound.setInteger("ticksLeft", ticksLeft);

        tagCompound.setFloat("heatCapacity", heatCapacity);
        tagCompound.setInteger("ingredientCapacity", ingredientCapacity);
        tagCompound.setFloat("maxUnstability", maxUnstability);

        NBTTagList tagList = new NBTTagList();
        for (IngredientProperties properties : cauldronContent) {
            NBTTagString tag = new NBTTagString(properties.name());
            tagList.appendTag(tag);
        }
        tagCompound.setTag("ingredients", tagList);
    }

    @Override
    public final Packet getDescriptionPacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
        S35PacketUpdateTileEntity packet = new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, nbt);
        return packet;
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        readFromNBT(pkt.func_148857_g());
    }

    @Override
    public void markDirty() {
        super.markDirty();
        if (worldObj.isRemote) return;
        this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }
}
