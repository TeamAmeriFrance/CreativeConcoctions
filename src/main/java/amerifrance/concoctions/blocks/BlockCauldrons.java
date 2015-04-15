package amerifrance.concoctions.blocks;

import amerifrance.concoctions.CreativeConcoctions;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCauldrons extends BlockCauldronBase {

    public BlockCauldrons() {
        super(Material.iron);
        setHardness(3.0F);
        setCreativeTab(CreativeConcoctions.tabConcoction);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return null;
    }
}
