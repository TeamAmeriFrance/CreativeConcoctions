package amerifrance.concoctions.api.ingredients;

import java.util.Arrays;
import java.util.List;

public class Ingredient {

    public final IngredientType ingredientType;
    public final float stability;
    public final int potency;
    private final IngredientProperties[] properties;

    public Ingredient(IngredientType ingredientType, float stability, int potency, IngredientProperties[] properties) {
        this.ingredientType = ingredientType;
        this.stability = stability;
        this.potency = potency;
        this.properties = properties;
    }

    public List<IngredientProperties> getProperties() {
        return Arrays.asList(properties);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        if (potency != that.potency) return false;
        if (Float.compare(that.stability, stability) != 0) return false;
        if (ingredientType != that.ingredientType) return false;
        if (!Arrays.equals(properties, that.properties)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = ingredientType != null ? ingredientType.hashCode() : 0;
        result = 31 * result + (stability != +0.0f ? Float.floatToIntBits(stability) : 0);
        result = 31 * result + potency;
        result = 31 * result + (properties != null ? Arrays.hashCode(properties) : 0);
        return result;
    }
}
