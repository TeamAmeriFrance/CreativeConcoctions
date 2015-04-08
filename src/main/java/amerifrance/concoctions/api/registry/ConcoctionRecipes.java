package amerifrance.concoctions.api.registry;

import amerifrance.concoctions.api.concoctions.Concoction;
import amerifrance.concoctions.api.ingredients.Ingredient;
import amerifrance.concoctions.api.ingredients.IngredientProperties;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.ArrayList;

public class ConcoctionRecipes {

    private static BiMap<IngredientProperties[], Concoction> registry = HashBiMap.create();

    public static void addRecipe(IngredientProperties[] properties, Concoction concoction) {
        if (registry.containsKey(properties))
            throw new IllegalArgumentException("Duplicate ingredients: " + properties);
        else registry.put(properties, concoction);
    }

    public static Concoction getConcoctionForIngredients(ArrayList<Ingredient> ingredients) {
        ArrayList<IngredientProperties> properties = new ArrayList<IngredientProperties>();
        for (Ingredient ingredient : ingredients) properties.addAll(ingredient.getPropertiesList());

        return registry.get(properties.toArray(new IngredientProperties[properties.size()]));
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
