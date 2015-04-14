package amerifrance.concoctions.registry;

import amerifrance.concoctions.blocks.BlockCauldronTest;
import amerifrance.concoctions.blocks.BlockInvisiLight;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlocksRegistry {

    public static BlockCauldronTest cauldronTest = new BlockCauldronTest();
    public static BlockInvisiLight invisiLight = new BlockInvisiLight();

    public static void registerBlocks() {
        GameRegistry.registerBlock(cauldronTest, "TestCauldron").setBlockName("CauldronTest");
        GameRegistry.registerBlock(invisiLight, "BlockInvisiLight");
    }
}
