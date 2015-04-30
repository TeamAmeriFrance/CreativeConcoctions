package amerifrance.concoctions.util.event;

import amerifrance.concoctions.api.ingredients.IPropertiesContainer;
import amerifrance.concoctions.api.ingredients.IngredientProperty;
import amerifrance.concoctions.concoctions.basic.ConcoctionKnowledge;
import amerifrance.concoctions.items.ItemBottledIngredient;
import amerifrance.concoctions.items.ItemConcoction;
import amerifrance.concoctions.items.ItemKnowledgePhial;
import amerifrance.concoctions.registry.ItemsRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;

public class AnvilHandler {

    @SubscribeEvent
    public void anvil(AnvilUpdateEvent event) {
        ItemStack left = event.left;
        ItemStack right = event.right;

        if (left == null || right == null) return;

        if (left.getItem() instanceof ItemConcoction && ItemConcoction.getConcoction(left) != null && ItemConcoction.getConcoction(left) instanceof ConcoctionKnowledge) {
            if (right.getItem() instanceof ItemBottledIngredient) {
                IPropertiesContainer propertiesContainer = (IPropertiesContainer) right.getItem();

                if (propertiesContainer.getIngredientProperties(right).size() == 1) {
                    IngredientProperty ingredientProperty = propertiesContainer.getIngredientProperties(right).get(0);

                    ItemStack output = new ItemStack(ItemsRegistry.knowledgePhial);
                    ItemKnowledgePhial.setProperty(output, ingredientProperty);

                    event.output = output.copy();
                    event.cost = 30;
                }
            }
        }
    }
}
