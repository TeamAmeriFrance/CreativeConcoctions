package amerifrance.concoctions.blocks;

import amerifrance.concoctions.tile.TileCauldronBase;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCauldronBase extends BlockContainer {

    public BlockCauldronBase(Material material) {
        super(material);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return null;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        TileCauldronBase cauldronBase = (TileCauldronBase) world.getTileEntity(x, y, z);
        if (cauldronBase.checkAndCraft(player.getHeldItem())) {
            return true;
        }
        return false;
    }
}
