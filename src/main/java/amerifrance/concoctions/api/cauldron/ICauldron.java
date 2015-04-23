package amerifrance.concoctions.api.cauldron;

import amerifrance.concoctions.api.ingredients.Ingredient;

public interface ICauldron {

    public void meltCauldron();

    public void cauldronUnstable();

    public void cauldronOverflow();

    public int getIngredientCapacity();

    public float getHeatCapacity();

    public float getMaxInstability();

    public float getHeat();

    public float getInstability();

    public boolean checkRecipe();

    public boolean canCraft();

    public void handleHeat();

    public void addIngredient(Ingredient ingredient, int stacksize);

    public float getLiquidHeightForRender();
}
