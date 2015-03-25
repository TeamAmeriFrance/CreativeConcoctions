package amerifrance.concoctions.api.ingredients;

public class Ingredient {

    public final IngredientType ingredientType;
    public final float stability;
    public final int potency;
    public final IngredientProperties[] properties;

    public Ingredient(IngredientType ingredientType, float stability, int potency, IngredientProperties... properties) {
        this.ingredientType = ingredientType;
        this.stability = stability;
        this.potency = potency;
        this.properties = properties;
    }
}
