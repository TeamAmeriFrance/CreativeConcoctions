package amerifrance.concoctions.api.registry;

import amerifrance.concoctions.api.concoctions.Concoction;
import amerifrance.concoctions.api.ingredients.Ingredient;
import amerifrance.concoctions.api.ingredients.IngredientProperties;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.ArrayList;

public class ConcoctionRecipes {

    private static BiMap<IngredientProperties[], Concoction> registry = HashBiMap.create();

    public static void addRecipe(IngredientProperties[] ingredients, Concoction concoction) {
        if (registry.containsKey(ingredients))
            throw new IllegalArgumentException("Duplicate ingredients: " + ingredients);
        else registry.put(ingredients, concoction);
    }

    public static Concoction getConcoctionForIngredients(Ingredient[] ingredients) {
        return registry.get(ingredients);
    }

    public static IngredientProperties[] getIngredientsForConcoction(Concoction concoction) {
        return registry.inverse().get(concoction);
    }

    public static boolean isMapEmtpy() {
        return registry.isEmpty();
    }

    public static int getMapSize() {
        return registry.size();
    }

    public static ArrayList<IngredientProperties[]> getKeys() {
        return new ArrayList<IngredientProperties[]>(registry.keySet());
    }

    public static ArrayList<Concoction> getConcoctions() {
        return new ArrayList<Concoction>(registry.values());
    }
}
