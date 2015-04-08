package amerifrance.concoctions.tile;

import amerifrance.concoctions.api.CreativeConcoctionsAPI;
import amerifrance.concoctions.api.MetaBlock;
import amerifrance.concoctions.api.concoctions.Concoction;
import amerifrance.concoctions.api.ingredients.Ingredient;
import amerifrance.concoctions.api.registry.ConcoctionRecipes;
import amerifrance.concoctions.api.registry.HeatSourceRegistry;
import amerifrance.concoctions.registry.ItemsRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;

public abstract class TileCauldronBase extends TileEntity {

    private final int ingredientCapacity;
    private final float heatCapacity;
    private final float maxUnstability;

    public ArrayList<Ingredient> cauldronContent;
    public float stability;
    public int potency;
    public float heat;
    public int ticksLeft;

    public TileCauldronBase(int ingredientCapacity, float heatCapacity, float maxUnstability) {
        this.ingredientCapacity = ingredientCapacity;
        this.heatCapacity = heatCapacity;
        this.maxUnstability = maxUnstability;

        this.cauldronContent = new ArrayList<Ingredient>();
        this.stability = 0F;
        this.heat = 0F;
        this.potency = 0;
        this.ticksLeft = 0;
    }

    public abstract void meltCauldron();

    public abstract void cauldronUnstable();

    public abstract void cauldronOverflow();

    public abstract void invalidRecipe(ItemStack stack);

    public int getIngredientCapacity() {
        return ingredientCapacity;
    }

    public float getHeatCapacity() {
        return heatCapacity;
    }

    public float getMaxUnstability() {
        return maxUnstability;
    }

    private boolean checkRecipe() {
        return ConcoctionRecipes.getConcoctionForIngredients(cauldronContent) != null;
    }

    private boolean canCraft() {
        return ticksLeft == 0 && heat >= getHeatCapacity() / 2;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (heat > getHeatCapacity()) meltCauldron();
        if (cauldronContent.size() > getIngredientCapacity()) cauldronOverflow();
        if (stability < getMaxUnstability()) cauldronUnstable();
        if (ticksLeft > 0) ticksLeft--;
        if (ticksLeft < 0) ticksLeft = 0;

        if (!worldObj.isRemote) handleHeat();
    }

    public void handleHeat() {
        if (!worldObj.isAirBlock(xCoord, yCoord - 1, zCoord)) {
            Block block = worldObj.getBlock(xCoord, yCoord - 1, zCoord);
            int metadata = worldObj.getBlockMetadata(xCoord, yCoord - 1, zCoord);
            MetaBlock metaBlock = new MetaBlock(block, metadata);

            if (HeatSourceRegistry.contains(metaBlock) && worldObj.getTotalWorldTime() % HeatSourceRegistry.getTimeToWait(metaBlock) == 0) {
                heat++;
            }
        }
    }

    public boolean checkAndCraft(ItemStack stack) {
        if (stack != null && stack.getItem() == ItemsRegistry.concoctionItem && canCraft()) {
            if (checkRecipe()) {
                Concoction concoction = ConcoctionRecipes.getConcoctionForIngredients(cauldronContent);
                int level = (int) (potency * Math.abs(heat));
                int duration = (int) (potency * Math.abs(stability));
                if (level > concoction.maxLevel) level = concoction.maxLevel;

                CreativeConcoctionsAPI.setConcoctionContext(stack, concoction, level, duration);
                return true;
            } else {
                invalidRecipe(stack);
                return false;
            }
        }
        return false;
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        this.stability = tagCompound.getFloat("stability");
        this.potency = tagCompound.getInteger("potency");
        this.heat = tagCompound.getFloat("heat");
        this.ticksLeft = tagCompound.getInteger("ticksLeft");

        NBTTagList tagList = tagCompound.getTagList("ingredients", Constants.NBT.TAG_COMPOUND);
        if (tagList != null) {
            for (int i = 0; i < tagList.tagCount(); i++) {
                NBTTagCompound tag = tagList.getCompoundTagAt(i);
                cauldronContent.add(Ingredient.readFromNBT(tag));
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        tagCompound.setFloat("stability", stability);
        tagCompound.setInteger("potency", potency);
        tagCompound.setFloat("heat", heat);
        tagCompound.setInteger("ticksLeft", ticksLeft);

        NBTTagList tagList = new NBTTagList();
        for (Ingredient ingredient : cauldronContent) {
            NBTTagCompound tag = new NBTTagCompound();
            ingredient.writeToNBT(tag);
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
