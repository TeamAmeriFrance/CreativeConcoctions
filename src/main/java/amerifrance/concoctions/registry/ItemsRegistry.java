package amerifrance.concoctions.registry;

import amerifrance.concoctions.items.ItemConcoction;
import amerifrance.concoctions.items.ItemCreativeConcoction;
import amerifrance.concoctions.items.ItemIngredients;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ItemsRegistry {

    public static Item creativeConcoction;
    public static Item ingredients;
    public static Item concoctionItem;

    public static void registerItems() {
        creativeConcoction = new ItemCreativeConcoction();
        GameRegistry.registerItem(creativeConcoction, "ItemCreativeConcoction");

        ingredients = new ItemIngredients();
        GameRegistry.registerItem(ingredients, "ItemIngredients");

        concoctionItem = new ItemConcoction();
        GameRegistry.registerItem(concoctionItem, "ItemConcoction");
    }
}
