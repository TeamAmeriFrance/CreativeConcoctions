package amerifrance.concoctions.api.registry;

import amerifrance.concoctions.api.concoctions.Concoction;
import amerifrance.concoctions.api.ingredients.IngredientProperties;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.ArrayList;
import java.util.List;

public class ConcoctionRecipes {

    private static BiMap<List<IngredientProperties>, Concoction> registry = HashBiMap.create();

    public static void addRecipe(List<IngredientProperties> properties, Concoction concoction) {
        if (registry.containsKey(properties))
            throw new IllegalArgumentException("Duplicate ingredients: " + properties);
        else registry.put(properties, concoction);
    }

    public static Concoction getConcoctionForIngredients(List<IngredientProperties> properties) {
        return registry.get(properties);
    }

    public static List<IngredientProperties> getIngredientsForConcoction(Concoction concoction) {
        return registry.inverse().get(concoction);
    }

    public static boolean isMapEmtpy() {
        return registry.isEmpty();
    }

    public static int getMapSize() {
        return registry.size();
    }

    public static List<List<IngredientProperties>> getKeys() {
        return new ArrayList<List<IngredientProperties>>(registry.keySet());
    }

    public static List<Concoction> getConcoctions() {
        return new ArrayList<Concoction>(registry.values());
    }
}
