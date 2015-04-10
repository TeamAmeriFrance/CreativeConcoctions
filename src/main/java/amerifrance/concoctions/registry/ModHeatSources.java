package amerifrance.concoctions.registry;

import amerifrance.concoctions.api.MetaBlock;
import amerifrance.concoctions.api.cauldron.HeatSource;
import amerifrance.concoctions.api.registry.HeatSourceRegistry;
import net.minecraft.init.Blocks;

public class ModHeatSources {

    public static MetaBlock redstoneTorch = new MetaBlock(Blocks.redstone_torch);
    public static MetaBlock torch = new MetaBlock(Blocks.torch);
    public static MetaBlock fire = new MetaBlock(Blocks.fire);
    public static MetaBlock flowingLava = new MetaBlock(Blocks.flowing_lava);
    public static MetaBlock lava = new MetaBlock(Blocks.lava);

    public static void registerHeatSources() {
        HeatSourceRegistry.registerHeatSource(redstoneTorch, new HeatSource(2400, 15));
        HeatSourceRegistry.registerHeatSource(torch, new HeatSource(1200, 30));
        HeatSourceRegistry.registerHeatSource(fire, new HeatSource(600, 60));
        HeatSourceRegistry.registerHeatSource(flowingLava, new HeatSource(300, 120));
        HeatSourceRegistry.registerHeatSource(lava, new HeatSource(150, 240));
    }
}
