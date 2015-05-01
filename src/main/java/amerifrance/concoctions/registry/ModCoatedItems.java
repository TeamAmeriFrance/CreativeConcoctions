package amerifrance.concoctions.registry;

import amerifrance.concoctions.ConfigHandler;
import amerifrance.concoctions.api.registry.CoatedItemsRegistry;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;

public class ModCoatedItems {

    public static void registerCoatedItems() {
        registerSwords();
        registerCustomCoatedItems();
    }

    public static void registerSwords() {
        for (Item item : GameData.getItemRegistry().typeSafeIterable()) {
            if (item instanceof ItemSword) {
                CoatedItemsRegistry.registerCoatedItem(item, 64);
            }
        }
    }

    public static void registerCustomCoatedItems() {
        for (String s : ConfigHandler.customCoatedItems) {
            String[] splitSource = s.split(":");

            String modid = splitSource[0];
            String name = splitSource[1];
            Item item = null;
            if (GameRegistry.findItem(modid, name) != null) {
                item = GameRegistry.findItem(modid, name);
            } else if (GameRegistry.findBlock(modid, name) != null) {
                item = Item.getItemFromBlock(GameRegistry.findBlock(modid, name));
            }
            int usesPerConcoction = Integer.parseInt(splitSource[2]);

            if (item != null)
                CoatedItemsRegistry.registerCoatedItem(item, usesPerConcoction);
        }
    }
}
