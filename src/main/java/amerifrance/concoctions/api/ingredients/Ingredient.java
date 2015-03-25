package amerifrance.concoctions.api.ingredients;

import java.util.Arrays;
import java.util.List;

public class Ingredient {

    public final IngredientType ingredientType;
    public final float stability;
    public final int potency;
    private final IngredientProperties[] properties;

    public Ingredient(IngredientType ingredientType, float stability, int potency, IngredientProperties... properties) {
        this.ingredientType = ingredientType;
        this.stability = stability;
        this.potency = potency;
        this.properties = properties;
    }

    public List<IngredientProperties> getProperties() {
        return Arrays.asList(properties);
    }
}
