package amerifrance.concoctions.api.cauldron;

import amerifrance.concoctions.api.ingredients.Ingredient;
import net.minecraft.item.ItemStack;

public interface ICauldron {

    public void meltCauldron();

    public void cauldronUnstable();

    public void cauldronOverflow();

    public void invalidRecipe(ItemStack stack);

    public int getIngredientCapacity();

    public float getHeatCapacity();

    public float getMaxUnstability();

    public float getHeat();

    public float getStability();

    public boolean checkRecipe();

    public boolean canCraft();

    public void handleHeat();

    public void addIngredient(Ingredient ingredient);
}
