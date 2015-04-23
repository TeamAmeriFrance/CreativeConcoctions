package amerifrance.concoctions.api.registry;

import amerifrance.concoctions.api.util.MetaBlock;
import amerifrance.concoctions.api.cauldron.HeatSource;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.ArrayList;
import java.util.List;

public class HeatSourceRegistry {

    private static BiMap<MetaBlock, HeatSource> registry = HashBiMap.create();

    public static void registerHeatSource(MetaBlock metaBlock, HeatSource heatSource) {
        if (registry.containsKey(metaBlock)) {
            throw new IllegalArgumentException("Duplicate heat source: " + metaBlock);
        } else {
            registry.put(metaBlock, heatSource);
        }
    }

    public static boolean contains(MetaBlock metaBlock) {
        return registry.containsKey(metaBlock);
    }

    public static HeatSource getHeatSource(MetaBlock metaBlock) {
        return registry.get(metaBlock);
    }

    public static int getSize() {
        return registry.size();
    }

    public static boolean isEmtpy() {
        return registry.isEmpty();
    }

    public static List<MetaBlock> getKeys() {
        return new ArrayList<MetaBlock>(registry.keySet());
    }

    public static List<HeatSource> getValues() {
        return new ArrayList<HeatSource>(registry.values());
    }
}
