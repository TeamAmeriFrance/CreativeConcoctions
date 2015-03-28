package amerifrance.concoctions.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ItemsRegistry {

    public static Item creativeConcoction;
    public static Item ingredients;

    public static void registerItems() {
        creativeConcoction = new ItemCreativeConcoction();
        GameRegistry.registerItem(creativeConcoction, "ItemCreativeConcoction");

        ingredients = new ItemIngredients();
        GameRegistry.registerItem(ingredients, "ItemIngredients");
    }
}
