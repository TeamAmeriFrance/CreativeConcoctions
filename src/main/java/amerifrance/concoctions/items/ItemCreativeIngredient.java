package amerifrance.concoctions.items;

import amerifrance.concoctions.CreativeConcoctions;
import amerifrance.concoctions.ModInformation;
import amerifrance.concoctions.api.ingredients.IPropertiesContainer;
import amerifrance.concoctions.api.ingredients.IngredientProperty;
import com.google.common.collect.Lists;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import java.util.List;

public class ItemCreativeIngredient extends Item implements IPropertiesContainer {

    public ItemCreativeIngredient() {
        setCreativeTab(CreativeConcoctions.tabConcoction);
        setUnlocalizedName(ModInformation.ID + ".creative.ingredient");
        setTextureName(ModInformation.TEXLOC + "ingredient_creative");
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs par2CreativeTabs, List list) {
        for (int i = 0; i < IngredientProperty.values().length; i++) {
            list.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean simulate) {
        list.add(EnumChatFormatting.DARK_GRAY + StatCollector.translateToLocal("tooltip.creative.only"));
        list.add(IngredientProperty.values()[stack.getItemDamage()].getLocalizedString());
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }


    @Override
    public List<IngredientProperty> getIngredientProperties(ItemStack stack) {
        return Lists.newArrayList(IngredientProperty.values()[stack.getItemDamage()]);
    }

    @Override
    public void setIngredientProperties(ItemStack stack, List<IngredientProperty> ingredientProperties) {

    }

    @Override
    public int getIngredientPotency(ItemStack stack) {
        return 5;
    }

    @Override
    public void setIngredientPotency(ItemStack stack, int potency) {

    }
}
