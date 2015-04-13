package amerifrance.concoctions.registry;

import amerifrance.concoctions.api.ingredients.Ingredient;
import amerifrance.concoctions.api.ingredients.IngredientProperties;
import amerifrance.concoctions.api.ingredients.IngredientType;
import amerifrance.concoctions.api.registry.IngredientsRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ModIngredients {

    public static Ingredient water = new Ingredient(IngredientType.NEUTRAL, 0, 0, new IngredientProperties[]{IngredientProperties.WATER}, 150);

    public static void registerIngredients() {
        registerWaters();
        registerArmors();
        ir(new Ingredient(IngredientType.PROCESSING_PRODUCT, 1, 2, new IngredientProperties[]{IngredientProperties.CATALYST, IngredientProperties.SPEED}, 50), Items.sugar);
        ir(new Ingredient(IngredientType.PROCESSING_PRODUCT, 8, 4, new IngredientProperties[]{IngredientProperties.CATALYST, IngredientProperties.SPEED, IngredientProperties.UNSTABLE}, 200), Items.firework_charge);
        ir(new Ingredient(IngredientType.PROCESSING_PRODUCT, 12, 8, new IngredientProperties[]{IngredientProperties.CATALYST, IngredientProperties.SPEED, IngredientProperties.UNSTABLE, IngredientProperties.EXPLOSIVE}, 300), Items.fireworks);
    }

    public static void registerWaters() {
        ir(water, Items.water_bucket);
        ir(water, Blocks.water);
        ir(water, Blocks.flowing_water);
        ir(water, Items.potionitem);
    }

    public static void registerArmors() {
        Ingredient protection = new Ingredient(IngredientType.NEUTRAL, 5, 2, new IngredientProperties[]{IngredientProperties.PROTECTION}, 500);
        ir(protection, Items.diamond_chestplate);
    }

    private static void ir(Ingredient ingredient, Object o) {
        if (o instanceof ItemStack) IngredientsRegistry.registerIngredient(ingredient, (ItemStack) o);
        else if (o instanceof Item) IngredientsRegistry.registerIngredient(ingredient, (Item) o);
        else if (o instanceof Block) IngredientsRegistry.registerIngredient(ingredient, (Block) o);
        else throw new IllegalArgumentException("Not a itemstack/item/block");
    }
}
