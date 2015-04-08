package amerifrance.concoctions.registry;

import amerifrance.concoctions.api.MetaBlock;
import amerifrance.concoctions.api.registry.HeatSourceRegistry;
import net.minecraft.init.Blocks;

public class ModHeatSources {

    public static MetaBlock redstoneTorch = new MetaBlock(Blocks.redstone_torch);
    public static MetaBlock torch = new MetaBlock(Blocks.torch);
    public static MetaBlock fire = new MetaBlock(Blocks.fire);
    public static MetaBlock flowingLava = new MetaBlock(Blocks.flowing_lava);
    public static MetaBlock lava = new MetaBlock(Blocks.lava);

    public static void registerHeatSources() {
        HeatSourceRegistry.registerHeatSource(redstoneTorch, 2400);
        HeatSourceRegistry.registerHeatSource(torch, 1200);
        HeatSourceRegistry.registerHeatSource(fire, 600);
        HeatSourceRegistry.registerHeatSource(flowingLava, 300);
        HeatSourceRegistry.registerHeatSource(lava, 150);
    }
}
