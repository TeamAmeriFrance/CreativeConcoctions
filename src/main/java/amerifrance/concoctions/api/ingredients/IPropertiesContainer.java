package amerifrance.concoctions.api.ingredients;

import net.minecraft.item.ItemStack;

public interface IPropertiesContainer {

    public IngredientProperties[] getIngredientProperties(ItemStack stack);

    public int getPotency(ItemStack stack);
}
