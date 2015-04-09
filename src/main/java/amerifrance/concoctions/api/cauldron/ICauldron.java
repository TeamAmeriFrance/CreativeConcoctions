package amerifrance.concoctions.api.cauldron;

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
}
