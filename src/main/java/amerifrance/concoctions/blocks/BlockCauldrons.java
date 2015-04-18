package amerifrance.concoctions.blocks;

import amerifrance.concoctions.CreativeConcoctions;
import amerifrance.concoctions.ModInformation;
import amerifrance.concoctions.tile.TileCauldron;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.List;

public class BlockCauldrons extends BlockCauldronBase {

    public BlockCauldrons() {
        super(Material.iron);
        setHardness(3.0F);
        setCreativeTab(CreativeConcoctions.tabConcoction);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        switch (metadata) {
            default:
                return null;
            case 0:
                return new TileCauldron.TileCauldronWood();
            case 1:
                return new TileCauldron.TileCauldronStone();
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir) {
        this.innerSide[0] = ir.registerIcon(ModInformation.TEXLOC + "wood/" + getTextureName() + "_inner");
        this.topSide[0] = ir.registerIcon(ModInformation.TEXLOC + "wood/" + getTextureName() + "_top");
        this.bottomSide[0] = ir.registerIcon(ModInformation.TEXLOC + "wood/" + getTextureName() + "_" + "bottom");
        this.outSide[0] = ir.registerIcon(ModInformation.TEXLOC + "wood/" + getTextureName() + "_side");
        this.itemTopSide[0] = ir.registerIcon(ModInformation.TEXLOC + "wood/" + getTextureName() + "_item_top");

        this.innerSide[1] = ir.registerIcon(ModInformation.TEXLOC + "stone/" + getTextureName() + "_inner");
        this.topSide[1] = ir.registerIcon(ModInformation.TEXLOC + "stone/" + getTextureName() + "_top");
        this.bottomSide[1] = ir.registerIcon(ModInformation.TEXLOC + "stone/" + getTextureName() + "_" + "bottom");
        this.outSide[1] = ir.registerIcon(ModInformation.TEXLOC + "stone/" + getTextureName() + "_side");
        this.itemTopSide[1] = ir.registerIcon(ModInformation.TEXLOC + "stone/" + getTextureName() + "_item_top");

        this.innerSide[2] = ir.registerIcon(ModInformation.TEXLOC + "iron/" + getTextureName() + "_inner");
        this.topSide[2] = ir.registerIcon(ModInformation.TEXLOC + "iron/" + getTextureName() + "_top");
        this.bottomSide[2] = ir.registerIcon(ModInformation.TEXLOC + "iron/" + getTextureName() + "_" + "bottom");
        this.outSide[2] = ir.registerIcon(ModInformation.TEXLOC + "iron/" + getTextureName() + "_side");
        this.itemTopSide[2] = ir.registerIcon(ModInformation.TEXLOC + "iron/" + getTextureName() + "_item_top");
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tabs, List list) {
        for (int i = 0; i < 3; i++) list.add(new ItemStack(item, 1, i));
    }

    @Override
    public int damageDropped(int meta) {
        return meta;
    }
}
