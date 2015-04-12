package amerifrance.concoctions.registry;

import amerifrance.concoctions.api.ingredients.Ingredient;
import amerifrance.concoctions.api.ingredients.IngredientProperties;
import amerifrance.concoctions.api.ingredients.IngredientType;
import amerifrance.concoctions.api.registry.IngredientsRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

public class ModIngredients {

    public static void registerIngredients() {
        registerWaters();
    }

    public static void registerWaters() {
        Ingredient water = new Ingredient(IngredientType.NEUTRAL, 0, 0, new IngredientProperties[]{IngredientProperties.WATER}, 150);
        IngredientsRegistry.registerIngredient(water, Items.water_bucket);
        IngredientsRegistry.registerIngredient(water, Blocks.water);
        IngredientsRegistry.registerIngredient(water, Blocks.flowing_water);
        IngredientsRegistry.registerIngredient(water, Items.potionitem);
    }
}
