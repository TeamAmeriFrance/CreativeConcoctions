package amerifrance.concoctions.registry;

import amerifrance.concoctions.blocks.BlockCauldronTest;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;

public class BlocksRegistry {

    public static BlockCauldronTest cauldronTest = new BlockCauldronTest(Material.iron);

    public static void registerBlocks() {
        GameRegistry.registerBlock(cauldronTest, "TestCauldron").setBlockName("CauldronTest");
    }
}
