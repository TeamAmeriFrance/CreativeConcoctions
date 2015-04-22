package amerifrance.concoctions.items;

import amerifrance.concoctions.CreativeConcoctions;
import amerifrance.concoctions.ModInformation;
import amerifrance.concoctions.api.CreativeConcoctionsAPI;
import amerifrance.concoctions.api.ingredients.IPropertiesContainer;
import amerifrance.concoctions.api.ingredients.IngredientProperty;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class ItemBottledIngredient extends Item implements IPropertiesContainer {

    public static String LEVEL_TAG = "IngredientLevel";
    public static String PROPERTIES_TAG = "IngredientProperty";

    public ItemBottledIngredient() {
        setCreativeTab(CreativeConcoctions.tabConcoction);
        setUnlocalizedName(ModInformation.ID + ".bottled.ingredient");
        setTextureName(ModInformation.TEXLOC + "ingredient_bottled");
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    @Override
    public List<IngredientProperty> getIngredientProperties(ItemStack stack) {
        CreativeConcoctionsAPI.checkAndSetCompound(stack);
        List<IngredientProperty> list = new ArrayList<IngredientProperty>();
        NBTTagList tagList = stack.stackTagCompound.getTagList(PROPERTIES_TAG, Constants.NBT.TAG_STRING);
        if (tagList != null) {
            for (int i = 0; i < tagList.tagCount(); i++)
                list.add(IngredientProperty.valueOf(tagList.getStringTagAt(i)));
        }
        return list;
    }

    @Override
    public void setIngredientProperties(ItemStack stack, IngredientProperty... ingredientProperties) {
        NBTTagList tagList = new NBTTagList();
        for (IngredientProperty ingredientProperty : ingredientProperties) {
            NBTTagString tag = new NBTTagString(ingredientProperty.name());
            tagList.appendTag(tag);
        }
        stack.stackTagCompound.setTag(PROPERTIES_TAG, tagList);
    }

    @Override
    public int getIngredientPotency(ItemStack stack) {
        CreativeConcoctionsAPI.checkAndSetCompound(stack);
        return stack.stackTagCompound.getInteger(LEVEL_TAG);
    }

    @Override
    public void setIngredientPotency(ItemStack stack, int potency) {
        CreativeConcoctionsAPI.checkAndSetCompound(stack);
        stack.stackTagCompound.setInteger(LEVEL_TAG, potency);
    }
}
