package amerifrance.concoctions.blocks;

import amerifrance.concoctions.CreativeConcoctions;
import amerifrance.concoctions.ModInformation;
import amerifrance.concoctions.tile.TileCauldronTest;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCauldronTest extends BlockCauldronBase {

    public BlockCauldronTest() {
        super(Material.iron);
        setHardness(5.0F);
        setCreativeTab(CreativeConcoctions.tabConcoction);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileCauldronTest();
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir) {
        this.innerSide[0] = ir.registerIcon(ModInformation.TEXLOC + getTextureName() + "_" + "inner");
        this.topSide[0] = ir.registerIcon(ModInformation.TEXLOC + getTextureName() + "_top");
        this.bottomSide[0] = ir.registerIcon(ModInformation.TEXLOC + getTextureName() + "_" + "bottom");
        this.outSide[0] = ir.registerIcon(ModInformation.TEXLOC + getTextureName() + "_side");
        this.itemTopSide[0] = ir.registerIcon(ModInformation.TEXLOC + getTextureName() + "_item_top");
    }
}
