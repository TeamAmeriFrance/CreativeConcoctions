package amerifrance.concoctions.registry;

import amerifrance.concoctions.items.*;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemsRegistry {

    public static ItemIngredients ingredients;
    //public static ItemCreativeIngredient creativeIngredient;
    public static ItemBottledIngredient bottledIngredient;
    public static ItemKnowledgePhial knowledgePhial;
    //public static ItemCreativeConcoction creativeConcoction;
    public static ItemConcoction concoctionItem;
    //public static ItemCreativeConcoctionThrowable creativeConcoctionThrowable;
    public static ItemConcoctionThrowable concoctionThrowable;
    public static ItemConcoctionGun concoctionGun;
    public static ItemConcoctionBow concoctionBow;
    public static ItemCoatedArrow coatedArrow;

    public static void registerItems() {
        ingredients = new ItemIngredients();
        GameRegistry.registerItem(ingredients, "ItemIngredients");

        //creativeIngredient = new ItemCreativeIngredient();
        //GameRegistry.registerItem(creativeIngredient, "ItemCreativeIngredient");

        bottledIngredient = new ItemBottledIngredient();
        GameRegistry.registerItem(bottledIngredient, "ItemBottledIngredient");

        knowledgePhial = new ItemKnowledgePhial();
        GameRegistry.registerItem(knowledgePhial, "ItemKnowledgePhial");

        //creativeConcoction = new ItemCreativeConcoction();
        //GameRegistry.registerItem(creativeConcoction, "ItemCreativeConcoction");

        concoctionItem = new ItemConcoction();
        GameRegistry.registerItem(concoctionItem, "ItemConcoction");

        //creativeConcoctionThrowable = new ItemCreativeConcoctionThrowable();
        //GameRegistry.registerItem(creativeConcoctionThrowable, "ItemCreativeConcoctionThrowable");

        concoctionThrowable = new ItemConcoctionThrowable();
        GameRegistry.registerItem(concoctionThrowable, "ItemConcoctionThrowable");

        concoctionGun = new ItemConcoctionGun();
        GameRegistry.registerItem(concoctionGun, "ItemConcoctionGun");

        concoctionBow = new ItemConcoctionBow();
        GameRegistry.registerItem(concoctionBow, "ItemConcoctionBow");

        coatedArrow = new ItemCoatedArrow();
        GameRegistry.registerItem(coatedArrow, "ItemCoatedArrow");
    }
}
