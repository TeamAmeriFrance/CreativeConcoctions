package amerifrance.concoctions.api.registry;

import amerifrance.concoctions.api.concoctions.Concoction;
import amerifrance.concoctions.guide.GuideConcoctionsHelper;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.ArrayList;
import java.util.HashMap;

public class ConcoctionsRegistry {
    public static HashMap<Concoction, Boolean> enabledConcoctions = new HashMap<Concoction, Boolean>();
    private static BiMap<String, Concoction> registry = HashBiMap.create();

    public static void registerConcoction(Concoction concoction, String id) {
        if (enabledConcoctions.get(concoction)) {
            if (registry.containsKey(id)) throw new IllegalArgumentException("Duplicate concoction id: " + id);
            else {
                registry.put(id, concoction);
                if (concoction.getComponents().length == 0)
                    GuideConcoctionsHelper.addBasicConcoctionEntry(concoction);
                else
                    GuideConcoctionsHelper.addCompoundConcoctionEntry(concoction);
            }
        }
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
