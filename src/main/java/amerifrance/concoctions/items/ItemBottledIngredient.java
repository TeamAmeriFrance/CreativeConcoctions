package amerifrance.concoctions.items;

import amerifrance.concoctions.CreativeConcoctions;
import amerifrance.concoctions.ModInformation;
import amerifrance.concoctions.api.CreativeConcoctionsAPI;
import amerifrance.concoctions.api.ingredients.IPropertiesContainer;
import amerifrance.concoctions.api.ingredients.IngredientProperty;
import amerifrance.concoctions.api.util.NBTTags;
import com.google.common.collect.Lists;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class ItemBottledIngredient extends Item implements IPropertiesContainer {

    public ItemBottledIngredient() {
        setCreativeTab(CreativeConcoctions.tabConcoction);
        setUnlocalizedName(ModInformation.ID + ".bottled.ingredient");
        setTextureName(ModInformation.TEXLOC + "ingredient_bottled");
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs par2CreativeTabs, List list) {
        for (IngredientProperty ingredientProperty : IngredientProperty.values()) {
            ItemStack stack = new ItemStack(this);
            setIngredientProperties(stack, Lists.newArrayList(ingredientProperty));
            setIngredientPotency(stack, 8);
        }
    }

    @Override
    public List<IngredientProperty> getIngredientProperties(ItemStack stack) {
        CreativeConcoctionsAPI.checkAndSetCompound(stack);
        List<IngredientProperty> list = new ArrayList<IngredientProperty>();
        NBTTagList tagList = stack.stackTagCompound.getTagList(NBTTags.PROPERTIES_TAG, Constants.NBT.TAG_STRING);
        if (tagList != null) {
            for (int i = 0; i < tagList.tagCount(); i++)
                list.add(IngredientProperty.valueOf(tagList.getStringTagAt(i)));
        }
        return list;
    }

    @Override
    public void setIngredientProperties(ItemStack stack, List<IngredientProperty> ingredientProperties) {
        CreativeConcoctionsAPI.checkAndSetCompound(stack);
        NBTTagList tagList = new NBTTagList();
        for (IngredientProperty ingredientProperty : ingredientProperties) {
            NBTTagString tag = new NBTTagString(ingredientProperty.name());
            tagList.appendTag(tag);
        }
        stack.stackTagCompound.setTag(NBTTags.PROPERTIES_TAG, tagList);
    }

    @Override
    public int getIngredientPotency(ItemStack stack) {
        CreativeConcoctionsAPI.checkAndSetCompound(stack);
        return stack.stackTagCompound.getInteger(NBTTags.POTENCY_TAG);
    }

    @Override
    public void setIngredientPotency(ItemStack stack, int potency) {
        CreativeConcoctionsAPI.checkAndSetCompound(stack);
        stack.stackTagCompound.setInteger(NBTTags.POTENCY_TAG, potency);
    }

    @Override
    @SuppressWarnings("unchecked")
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean simulate) {
        list.add(String.format(StatCollector.translateToLocal("gui.text.potency"), String.valueOf(getIngredientPotency(stack))));
        for (IngredientProperty ingredientProperty : getIngredientProperties(stack)) {
            list.add(ingredientProperty.getLocalizedString());
        }
    }
}
