package amerifrance.concoctions.registry;

import amerifrance.concoctions.blocks.BlockCauldronTest;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;

public class BlocksRegistry {

    public static void registerBlocks() {
        GameRegistry.registerBlock(new BlockCauldronTest(Material.iron), "TestCauldron").setBlockName("CauldronTest");
    }
}
