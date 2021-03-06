package amerifrance.concoctions.items;

import amerifrance.concoctions.CreativeConcoctions;
import amerifrance.concoctions.ModInformation;
import amerifrance.concoctions.api.CreativeConcoctionsAPI;
import amerifrance.concoctions.api.ingredients.IngredientKnowledge;
import amerifrance.concoctions.api.ingredients.IngredientProperty;
import amerifrance.concoctions.api.util.NBTTags;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ItemKnowledgePhial extends Item {

    public ItemKnowledgePhial() {
        setCreativeTab(CreativeConcoctions.tabConcoction);
        setUnlocalizedName(ModInformation.ID + ".knowledge.phial");
        setMaxDamage(0);
        setMaxStackSize(1);
        setHasSubtypes(true);
    }

    public static IngredientProperty getProperty(ItemStack stack) {
        CreativeConcoctionsAPI.checkAndSetCompound(stack);
        if (stack.stackTagCompound.hasKey(NBTTags.PROPERTY_TAG))
            return IngredientProperty.valueOf(stack.stackTagCompound.getString(NBTTags.PROPERTY_TAG));
        else
            return null;
    }

    public static void setProperty(ItemStack stack, IngredientProperty ingredientProperty) {
        CreativeConcoctionsAPI.checkAndSetCompound(stack);
        stack.stackTagCompound.setString(NBTTags.PROPERTY_TAG, ingredientProperty.name());
    }

    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.drink;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (getProperty(stack) != null)
            player.setItemInUse(stack, getMaxItemUseDuration(stack));

        return stack;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 512;
    }

    @Override
    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
        if (getProperty(stack) != null) {
            IngredientKnowledge.getKnowledge(player).put(getProperty(stack), true);
            if (!player.capabilities.isCreativeMode) --stack.stackSize;
            if (stack.stackSize <= 0) return new ItemStack(Items.glass_bottle);
            player.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle));
        }
        return stack;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return getProperty(stack) != null;
    }

    @Override
    @SuppressWarnings("unchecked")
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean simulate) {
        if (getProperty(stack) != null) {
            String s = StringUtils.lowerCase(getProperty(stack).name());
            list.add(StringUtils.capitalize(s));
        }
    }
}
