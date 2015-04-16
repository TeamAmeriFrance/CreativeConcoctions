package amerifrance.concoctions.registry;

import amerifrance.concoctions.ModInformation;
import amerifrance.concoctions.blocks.BlockCauldronTest;
import amerifrance.concoctions.blocks.BlockCauldrons;
import amerifrance.concoctions.blocks.BlockInvisiLight;
import amerifrance.concoctions.items.blocks.ItemBlockCauldrons;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlocksRegistry {

    public static BlockCauldronTest cauldronTest = new BlockCauldronTest();
    public static BlockInvisiLight invisiLight = new BlockInvisiLight();
    public static BlockCauldrons cauldrons = new BlockCauldrons();

    public static void registerBlocks() {
        GameRegistry.registerBlock(cauldronTest, "TestCauldron").setBlockName("CauldronTest");
        GameRegistry.registerBlock(invisiLight, "BlockInvisiLight");
        GameRegistry.registerBlock(cauldrons, ItemBlockCauldrons.class, "BlockCauldrons").setBlockName(ModInformation.ID + ".cauldrons");
    }
}
