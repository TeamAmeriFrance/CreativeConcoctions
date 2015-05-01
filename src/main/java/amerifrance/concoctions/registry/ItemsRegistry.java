package amerifrance.concoctions.registry;

import amerifrance.concoctions.items.*;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

public class ItemsRegistry {

    public static Item.ToolMaterial COATED_MATERIAL = EnumHelper.addToolMaterial("CC_COATED", 3, 1024, 10.0F, 2.5F, 4);

    public static ItemIngredients ingredients;
    public static ItemBottledIngredient bottledIngredient;
    public static ItemKnowledgePhial knowledgePhial;
    public static ItemConcoction concoctionItem;
    public static ItemConcoctionThrowable concoctionThrowable;
    public static ItemConcoctionGun concoctionGun;
    public static ItemConcoctionBow concoctionBow;
    public static ItemCoatedArrow coatedArrow;

    public static void registerItems() {
        ingredients = new ItemIngredients();
        GameRegistry.registerItem(ingredients, "ItemIngredients");

        bottledIngredient = new ItemBottledIngredient();
        GameRegistry.registerItem(bottledIngredient, "ItemBottledIngredient");

        knowledgePhial = new ItemKnowledgePhial();
        GameRegistry.registerItem(knowledgePhial, "ItemKnowledgePhial");

        concoctionItem = new ItemConcoction();
        GameRegistry.registerItem(concoctionItem, "ItemConcoction");

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
