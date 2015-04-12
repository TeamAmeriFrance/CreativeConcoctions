package amerifrance.concoctions.api.ingredients;

import net.minecraft.item.ItemStack;

import java.util.List;

public interface IPropertiesContainer {

    public List<IngredientProperties> getIngredientProperties(ItemStack stack);
}
