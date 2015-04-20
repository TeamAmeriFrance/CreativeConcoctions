package amerifrance.concoctions.registry;

import amerifrance.concoctions.ModInformation;
import amerifrance.concoctions.blocks.BlockInvisiLight;
import amerifrance.concoctions.tile.TileCauldron;
import cpw.mods.fml.common.registry.GameRegistry;

public class TilesRegistry {

    public static void registerTiles() {
        GameRegistry.registerTileEntity(BlockInvisiLight.TileInvisibleLight.class, ModInformation.ID + ":TileInvisiLight");

        GameRegistry.registerTileEntity(TileCauldron.TileCauldronWood.class, ModInformation.ID + ":TileCauldronWood");
        GameRegistry.registerTileEntity(TileCauldron.TileCauldronStone.class, ModInformation.ID + ":TileCauldronStone");
        GameRegistry.registerTileEntity(TileCauldron.TileCauldronIron.class, ModInformation.ID + ":TileCauldronIron");
        GameRegistry.registerTileEntity(TileCauldron.TileCauldronGold.class, ModInformation.ID + ":TileCauldronGold");
        GameRegistry.registerTileEntity(TileCauldron.TileCauldronObsidian.class, ModInformation.ID + ":TileCauldronObsidian");
    }
}
