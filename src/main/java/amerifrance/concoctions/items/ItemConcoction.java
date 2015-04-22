package amerifrance.concoctions.items;

import amerifrance.concoctions.CreativeConcoctions;
import amerifrance.concoctions.ModInformation;
import amerifrance.concoctions.api.CreativeConcoctionsAPI;
import amerifrance.concoctions.api.concoctions.Concoction;
import amerifrance.concoctions.api.concoctions.ConcoctionsHelper;
import amerifrance.concoctions.api.ingredients.IPropertiesContainer;
import amerifrance.concoctions.api.ingredients.IngredientProperty;
import amerifrance.concoctions.api.registry.ConcoctionRecipes;
import amerifrance.concoctions.api.registry.ConcoctionsRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.List;

public class ItemConcoction extends Item implements IPropertiesContainer {

    public static String CONCOCTION_ID_TAG = "ConcoctionID";
    public static String CONCOCTION_LEVEL_TAG = "ConcoctionLevel";
    public static String CONCOCTION_DURATION_TAG = "ConcoctionDuration";

    public ItemConcoction() {
        setCreativeTab(CreativeConcoctions.tabConcoction);
        setUnlocalizedName(ModInformation.ID + ".concoction");
        setTextureName("minecraft:potion_bottle_empty");
        setMaxDamage(0);
        setMaxStackSize(1);
        setHasSubtypes(true);
    }

    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.drink;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (getConcoction(stack) != null)
            player.setItemInUse(stack, getMaxItemUseDuration(stack));

        return stack;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 32;
    }

    @Override
    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
        if (getConcoction(stack) != null) {
            ConcoctionsHelper.addConcoction(player, getConcoction(stack), getIngredientPotency(stack), getDuration(stack));
            if (!player.capabilities.isCreativeMode) --stack.stackSize;
            if (stack.stackSize <= 0) return new ItemStack(Items.glass_bottle);
            player.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle));
        }
        return stack;
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        if (getConcoction(stack) != null)
            return super.getItemStackDisplayName(stack) + " (" + getConcoction(stack).getConcotionType().prefix + getConcoction(stack).name + EnumChatFormatting.RESET + ")";
        else
            return super.getItemStackDisplayName(stack);
    }

    @Override
    @SuppressWarnings("unchecked")
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean simulate) {
        if (!GuiScreen.isShiftKeyDown()) return;

        if (getConcoction(stack) != null) {
            list.add(getConcoction(stack).getConcotionType().prefix + getConcoction(stack).name);
            list.add(String.format(StatCollector.translateToLocal("gui.text.level"), getIngredientPotency(stack)));
            list.add(String.format(StatCollector.translateToLocal("gui.text.duration"), (double) getDuration(stack) / 20 + "s"));
        }
    }

    @Override
    public List<IngredientProperty> getIngredientProperties(ItemStack stack) {
        if (getConcoction(stack) != null)
            return ConcoctionRecipes.getIngredientsForConcoction(getConcoction(stack));
        else
            return null;
    }

    @Override
    public void setIngredientProperties(ItemStack stack, IngredientProperty... ingredientProperties) {
    }

    @Override
    public int getIngredientPotency(ItemStack stack) {
        CreativeConcoctionsAPI.checkAndSetCompound(stack);
        return stack.stackTagCompound.getInteger(CONCOCTION_LEVEL_TAG);
    }

    @Override
    public void setIngredientPotency(ItemStack stack, int potency) {
        CreativeConcoctionsAPI.checkAndSetCompound(stack);
        stack.stackTagCompound.setInteger(CONCOCTION_LEVEL_TAG, potency);
    }

    public static Concoction getConcoction(ItemStack stack) {
        CreativeConcoctionsAPI.checkAndSetCompound(stack);
        return ConcoctionsRegistry.getConcoctionForId(stack.stackTagCompound.getString(CONCOCTION_ID_TAG));
    }


    public int getDuration(ItemStack stack) {
        CreativeConcoctionsAPI.checkAndSetCompound(stack);
        return stack.stackTagCompound.getInteger(CONCOCTION_DURATION_TAG);
    }

    public void setConcoction(ItemStack stack, Concoction concoction) {
        CreativeConcoctionsAPI.checkAndSetCompound(stack);
        stack.stackTagCompound.setString(CONCOCTION_ID_TAG, ConcoctionsRegistry.getIdForConcoction(concoction));
    }

    public void setDuration(ItemStack stack, int duration) {
        CreativeConcoctionsAPI.checkAndSetCompound(stack);
        stack.stackTagCompound.setInteger(CONCOCTION_DURATION_TAG, duration);
    }
}
