package amerifrance.concoctions.api.ingredients;

import net.minecraft.item.ItemStack;

import java.util.List;

public interface IPropertiesContainer {

    public List<IngredientProperties> getIngredientProperties(ItemStack stack);

    public void setIngredientProperties(ItemStack stack, IngredientProperties... properties);

    public int getIngredientPotency(ItemStack stack);

    public void setIngredientPotency(ItemStack stack, int potency);
}
