package amerifrance.concoctions.api.ingredients;

import java.util.Arrays;
import java.util.List;

import static java.lang.Float.compare;
import static java.lang.Float.floatToIntBits;
import static java.util.Arrays.asList;

public class Ingredient {

    public final IngredientType ingredientType;
    public final float instability;
    public final int potency;
    public final int ticksToBoil;
    private final IngredientProperty[] ingredientProperties;

    public Ingredient(IngredientType ingredientType, float instability, int potency, int ticksToBoil, IngredientProperty... ingredientProperties) {
        this.ingredientType = ingredientType;
        this.instability = instability;
        this.potency = potency;
        this.ticksToBoil = ticksToBoil;
        this.ingredientProperties = ingredientProperties;
    }

    public List<IngredientProperty> getPropertiesList() {
        return asList(ingredientProperties);
    }

    public IngredientProperty[] getProperties() {
        return Arrays.copyOf(ingredientProperties, ingredientProperties.length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        if (potency != that.potency) return false;
        if (compare(that.instability, instability) != 0) return false;
        if (ingredientType != that.ingredientType) return false;
        if (!Arrays.equals(ingredientProperties, ingredientProperties)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = ingredientType != null ? ingredientType.hashCode() : 0;
        result = 31 * result + (instability != +0.0f ? floatToIntBits(instability) : 0);
        result = 31 * result + potency;
        result = 31 * result + (ingredientProperties != null ? Arrays.hashCode(ingredientProperties) : 0);
        return result;
    }
}
