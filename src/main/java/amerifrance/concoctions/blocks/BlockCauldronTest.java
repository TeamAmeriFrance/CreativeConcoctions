package amerifrance.concoctions.blocks;

import amerifrance.concoctions.CreativeConcoctions;
import amerifrance.concoctions.tile.TileCauldronTest;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCauldronTest extends BlockCauldronBase {

    public BlockCauldronTest(Material material) {
        super(material);
        setHardness(5.0F);
        setCreativeTab(CreativeConcoctions.tabConcoction);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileCauldronTest();
    }
}
