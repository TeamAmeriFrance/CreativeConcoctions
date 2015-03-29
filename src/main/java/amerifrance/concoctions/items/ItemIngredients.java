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
        //TODO: change the potency and stability values
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 1, 1, new IngredientProperties[]{IngredientProperties.FLIGHT, IngredientProperties.POISON}), ingredientStacks[0]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 1, 1, new IngredientProperties[]{IngredientProperties.UNSTABLE, IngredientProperties.INVISIBILITY}), ingredientStacks[1]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 1, 1, new IngredientProperties[]{IngredientProperties.CATALYST, IngredientProperties.FLIGHT}), ingredientStacks[2]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 1, 1, new IngredientProperties[]{IngredientProperties.DAMAGE, IngredientProperties.SLOWING_DOWN}), ingredientStacks[3]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 1, 1, new IngredientProperties[]{IngredientProperties.YOUTH, IngredientProperties.STABILIZER}), ingredientStacks[4]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 1, 1, new IngredientProperties[]{IngredientProperties.MORPHING}), ingredientStacks[5]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 1, 1, new IngredientProperties[]{IngredientProperties.WATER_BREATHING, IngredientProperties.SWIMMING, IngredientProperties.MORPHING}), ingredientStacks[6]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 1, 1, new IngredientProperties[]{IngredientProperties.FIRE}), ingredientStacks[7]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 1, 1, new IngredientProperties[]{IngredientProperties.DAMAGE, IngredientProperties.POISON, IngredientProperties.INVISIBILITY, IngredientProperties.UNSTABLE}), ingredientStacks[8]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 2, 1, new IngredientProperties[]{IngredientProperties.MORPHING}), ingredientStacks[9]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 1, 1, new IngredientProperties[]{IngredientProperties.CONFUSION}), ingredientStacks[10]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 1, 1, new IngredientProperties[]{IngredientProperties.REGENERATION, IngredientProperties.HEALING, IngredientProperties.UNSTABLE}), ingredientStacks[11]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 1, 1, new IngredientProperties[]{IngredientProperties.REGENERATION, IngredientProperties.HEALING, IngredientProperties.CATALYST}), ingredientStacks[12]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 1, 1, new IngredientProperties[]{IngredientProperties.CLEANSING}), ingredientStacks[13]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 1, 1, new IngredientProperties[]{IngredientProperties.CURATIVE, IngredientProperties.STABILIZER}), ingredientStacks[14]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 1, 1, new IngredientProperties[]{IngredientProperties.STRENGTH}), ingredientStacks[15]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 2, 1, new IngredientProperties[]{IngredientProperties.CONFUSION}), ingredientStacks[16]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 3, 1, new IngredientProperties[]{IngredientProperties.CONFUSION}), ingredientStacks[17]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 1, 1, new IngredientProperties[]{IngredientProperties.SLOWING_DOWN}), ingredientStacks[18]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 2, 1, new IngredientProperties[]{IngredientProperties.SLOWING_DOWN}), ingredientStacks[19]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 3, 1, new IngredientProperties[]{IngredientProperties.SLOWING_DOWN}), ingredientStacks[20]);
        IngredientsRegistry.registerIngredient(new Ingredient(IngredientType.PLANT, 1, 1, new IngredientProperties[]{IngredientProperties.POISON}), ingredientStacks[21]);
    }
}
