package amerifrance.concoctions.items;

import amerifrance.concoctions.CreativeConcoctions;
import amerifrance.concoctions.ModInformation;
import amerifrance.concoctions.api.ingredients.Ingredient;
import amerifrance.concoctions.api.ingredients.IngredientProperties;
import amerifrance.concoctions.api.ingredients.IngredientType;
import amerifrance.concoctions.api.registry.IngredientsRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public class ItemIngredients extends Item {

    public String[] names = {"aconite", "asphodel", "belladona", "bubotuber", "daisy", "fluxweed", "gillyweed",
            "ginger", "hellebore", "knotgrass", "lovage", "mandrake", "nettles", "peppermint", "phytobeozar",
            "pomegranate", "scurvyGrass", "sneezewort", "sophorousBean", "valerian", "wormwood", "arachnidVenom",
            "humanFlesh", "humanBones", "humanBlood", "fishSpine", "fishEyes", "spiderLegs", "zombieBones", "zombieBlood"};

    public IIcon[] icon = new IIcon[names.length];
    public ItemStack[] ingredientStacks = new ItemStack[names.length];

    public ItemIngredients() {
        for (int i = 0; i < names.length; i++) ingredientStacks[i] = new ItemStack(this, 1, i);
        setCreativeTab(CreativeConcoctions.tabConcoction);
        setUnlocalizedName(ModInformation.ID + ".ingredient");
        setHasSubtypes(true);
        registerIngredients();
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        if (stack.getItemDamage() < names.length) {
            String name = names[stack.getItemDamage()];
            return getUnlocalizedName() + "." + name;
        }
        return super.getUnlocalizedName(stack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tabs, List list) {
        for (ItemStack stack : ingredientStacks) list.add(stack);
    }

    public void registerIngredients() {
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 2, 5, new IngredientProperties[]{IngredientProperties.FLIGHT, IngredientProperties.POISON}), ingredientStacks[0]);
    }
}
