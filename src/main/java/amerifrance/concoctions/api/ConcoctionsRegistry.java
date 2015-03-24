package amerifrance.concoctions.api;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.ArrayList;

public class ConcoctionsRegistry {
    private static BiMap<String, Concoction> registry = HashBiMap.create();

    public static void registerConcoction(Concoction concoction, String id) {
        if (registry.containsKey(id)) throw new IllegalArgumentException("Duplicate concoction id: " + id);
        else registry.put(id, concoction);
    }

    public static Concoction getConcoctionForId(String id) {
        return registry.get(id);
    }

    public static String getIdForConcoction(Concoction concoction) {
        return registry.inverse().get(concoction);
    }

    public static boolean isMapEmtpy() {
        return registry.isEmpty();
    }

    public static int getMapSize() {
        return registry.size();
    }

    public static ArrayList<String> getKeys() {
        return new ArrayList<String>(registry.keySet());
    }

    public static ArrayList<Concoction> getConcoctions() {
        return new ArrayList<Concoction>(registry.values());
    }
}
