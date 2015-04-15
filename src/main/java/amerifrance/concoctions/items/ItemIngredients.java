package amerifrance.concoctions.items;

import amerifrance.concoctions.CreativeConcoctions;
import amerifrance.concoctions.ModInformation;
import amerifrance.concoctions.api.ingredients.Ingredient;
import amerifrance.concoctions.api.ingredients.IngredientProperties;
import amerifrance.concoctions.api.ingredients.IngredientType;
import amerifrance.concoctions.api.registry.IngredientsRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public class ItemIngredients extends Item {

    public String[] names = {"aconite", "asphodel", "belladona", "bubotuber", "fluxweed", "gillyweed",
            "ginger", "hellebore", "knotgrass", "lovage", "mandrake", "nettles", "peppermint", "phytobezoar",
            "pomegranate", "scurvyGrass", "sneezewort", "sophorousBean", "valerian", "wormwood", "arachnidVenom",
            "humanFlesh", "humanBone", "humanBlood", "fishSpine", "fishEye", "spiderLeg", "zombieBone", "zombieBlood"};

    public IIcon[] icons = new IIcon[names.length];
    public ItemStack[] ingredientStacks = new ItemStack[names.length];

    public ItemIngredients() {
        for (int i = 0; i < names.length; i++) ingredientStacks[i] = new ItemStack(this, 1, i);
        setCreativeTab(CreativeConcoctions.tabConcoction);
        setUnlocalizedName(ModInformation.ID + ".ingredient");
        setHasSubtypes(true);
        setTextureName("ingredient");
        registerIngredients();
    }

    @Override
    public void registerIcons(IIconRegister ir) {
        for (int i = 0; i < names.length; i++) {
            this.icons[i] = ir.registerIcon(ModInformation.TEXLOC + getIconString() + "_" + names[i]);
        }
    }

    @Override
    public IIcon getIconFromDamage(int damage) {
        return icons[damage];
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
        //TODO: change the potency, stability and boiling time values
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 1, 1, 100, IngredientProperties.FLIGHT, IngredientProperties.POISON), ingredientStacks[0]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 1, 1, 100, IngredientProperties.UNSTABLE, IngredientProperties.INVISIBILITY), ingredientStacks[1]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 1, 1, 100, IngredientProperties.CATALYST, IngredientProperties.FLIGHT), ingredientStacks[2]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 1, 1, 100, IngredientProperties.DAMAGE, IngredientProperties.SLOWING_DOWN), ingredientStacks[3]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 1, 1, 100, IngredientProperties.MORPHING), ingredientStacks[4]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 1, 1, 100, IngredientProperties.WATER_BREATHING, IngredientProperties.SWIMMING, IngredientProperties.MORPHING), ingredientStacks[5]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 1, 1, 100, IngredientProperties.FIRE), ingredientStacks[6]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 1, 1, 100, IngredientProperties.DAMAGE, IngredientProperties.POISON, IngredientProperties.INVISIBILITY, IngredientProperties.UNSTABLE), ingredientStacks[7]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 2, 1, 100, IngredientProperties.MORPHING), ingredientStacks[8]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 1, 1, 100, IngredientProperties.CONFUSION), ingredientStacks[9]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 1, 1, 100, IngredientProperties.REGENERATION, IngredientProperties.HEALING, IngredientProperties.UNSTABLE), ingredientStacks[10]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 1, 1, 100, IngredientProperties.REGENERATION, IngredientProperties.HEALING, IngredientProperties.CATALYST), ingredientStacks[11]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 1, 1, 100, IngredientProperties.CLEANSING), ingredientStacks[12]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 1, 1, 100, IngredientProperties.CURATIVE, IngredientProperties.STABILIZER), ingredientStacks[13]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 1, 1, 100, IngredientProperties.STRENGTH), ingredientStacks[14]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 2, 1, 100, IngredientProperties.CONFUSION), ingredientStacks[15]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 3, 1, 100, IngredientProperties.CONFUSION), ingredientStacks[16]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 1, 1, 100, IngredientProperties.SLOWING_DOWN), ingredientStacks[17]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 2, 1, 100, IngredientProperties.SLOWING_DOWN), ingredientStacks[18]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 3, 1, 100, IngredientProperties.SLOWING_DOWN), ingredientStacks[19]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 1, 1, 100, IngredientProperties.POISON), ingredientStacks[20]);
    }
}
