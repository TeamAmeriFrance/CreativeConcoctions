package amerifrance.concoctions.registry;

import amerifrance.concoctions.ConfigHandler;
import amerifrance.concoctions.api.util.MetaBlock;
import amerifrance.concoctions.api.cauldron.HeatSource;
import amerifrance.concoctions.api.registry.HeatSourceRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class ModHeatSources {

    public static void registerHeatSources() {

        for (String source : ConfigHandler.customHeatSources) {
            String[] splitSource = source.split(":");

            String modid = splitSource[0];
            String blockName = splitSource[1];
            Block block = GameRegistry.findBlock(modid, blockName);
            int meta = Integer.parseInt(splitSource[2]);
            int waitTick = Integer.parseInt(splitSource[3]);
            int maxHeat = Integer.parseInt(splitSource[4]);

            HeatSourceRegistry.registerHeatSource(new MetaBlock(block, meta), new HeatSource(waitTick, maxHeat));
        }
    }
}
