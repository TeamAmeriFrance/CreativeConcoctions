package amerifrance.concoctions.registry;

import amerifrance.concoctions.items.ItemConcoction;
import amerifrance.concoctions.items.ItemCreativeConcoction;
import amerifrance.concoctions.items.ItemCreativeIngredient;
import amerifrance.concoctions.items.ItemIngredients;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemsRegistry {

    public static ItemCreativeConcoction creativeConcoction;
    public static ItemIngredients ingredients;
    public static ItemConcoction concoctionItem;
    public static ItemCreativeIngredient creativeIngredient;

    public static void registerItems() {
        creativeConcoction = new ItemCreativeConcoction();
        GameRegistry.registerItem(creativeConcoction, "ItemCreativeConcoction");

        ingredients = new ItemIngredients();
        GameRegistry.registerItem(ingredients, "ItemIngredients");

        concoctionItem = new ItemConcoction();
        GameRegistry.registerItem(concoctionItem, "ItemConcoction");

        creativeIngredient = new ItemCreativeIngredient();
        GameRegistry.registerItem(creativeIngredient, "ItemCreativeIngredient");
    }
}
