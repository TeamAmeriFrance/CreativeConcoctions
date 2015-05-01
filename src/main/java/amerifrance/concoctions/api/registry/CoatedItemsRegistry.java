package amerifrance.concoctions.api.registry;

import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.HashMap;

public class CoatedItemsRegistry {

    private static HashMap<Item, Integer> registry = new HashMap<Item, Integer>();

    public static void registerCoatedItem(Item item, int usesPerConcoctions) {
        if (registry.containsKey(item)) {
            throw new IllegalArgumentException("Duplicate coated item: " + item);
        } else {
            registry.put(item, usesPerConcoctions);
        }
    }

    public static boolean isEmpty() {
        return registry.isEmpty();
    }

    public static int getSize() {
        return registry.size();
    }

    public static int getUsesPerConcoctions(Item item) {
        return registry.get(item);
    }

    public static boolean hasKey(Item item) {
        return registry.containsKey(item);
    }

    public static ArrayList<Item> getCoatedItems() {
        return new ArrayList<Item>(registry.keySet());
    }
}
