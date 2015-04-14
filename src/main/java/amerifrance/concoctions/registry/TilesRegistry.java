package amerifrance.concoctions.registry;

import amerifrance.concoctions.ModInformation;
import amerifrance.concoctions.blocks.BlockInvisiLight;
import amerifrance.concoctions.tile.TileCauldronTest;
import cpw.mods.fml.common.registry.GameRegistry;

public class TilesRegistry {

    public static void registerTiles() {
        GameRegistry.registerTileEntity(TileCauldronTest.class, "TileCauldronTest");
        GameRegistry.registerTileEntity(BlockInvisiLight.TileInvisibleLight.class, ModInformation.ID + ":TileInvisiLight");
    }
}
