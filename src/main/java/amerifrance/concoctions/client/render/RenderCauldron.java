package amerifrance.concoctions.client.render;

import amerifrance.concoctions.blocks.BlockCauldronBase;
import amerifrance.concoctions.registry.BlocksRegistry;
import amerifrance.concoctions.tile.TileCauldronBase;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class RenderCauldron implements ISimpleBlockRenderingHandler {

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        renderer.setRenderBoundsFromBlock(block);
        Tessellator tessellator = Tessellator.instance;
        IIcon icon = BlocksRegistry.cauldronTest.getBlockTextureFromSide(2);
        IIcon top = BlocksRegistry.cauldronTest.getBlockTextureFromSide(7);
        IIcon bottom = BlocksRegistry.cauldronTest.getBlockTextureFromSide(0);

        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, icon);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, icon);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, icon);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, icon);
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, bottom);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, top);
        tessellator.draw();
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        renderer.renderStandardBlock(block, x, y, z);
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z));
        IIcon icon = BlocksRegistry.cauldronTest.getBlockTextureFromSide(2);
        float f4 = 0.125F;
        renderer.renderFaceXPos(block, x - 1.0F + f4, y, z, icon);
        renderer.renderFaceXNeg(block, x + 1.0F - f4, y, z, icon);
        renderer.renderFaceZPos(block, x, y, z - 1.0F + f4, icon);
        renderer.renderFaceZNeg(block, x, y, z + 1.0F - f4, icon);

        IIcon inner = BlocksRegistry.cauldronTest.innerSide;
        renderer.renderFaceYPos(block, x, y - 1.0F + 0.25F, z, inner);
        renderer.renderFaceYNeg(block, x, y + 1.0F - 0.75F, z, inner);

        TileCauldronBase cauldronBase = (TileCauldronBase) world.getTileEntity(x, y, z);
        IIcon water = BlockLiquid.getLiquidIcon("water_still");
        if (cauldronBase.getIngredientCapacity() != 0 && cauldronBase.cauldronContent.size() != 0)
            renderer.renderFaceYPos(block, x, y - 1.0F + cauldronBase.getLiquidHeightForRender(), z, water);

        return true;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    @Override
    public int getRenderId() {
        return BlockCauldronBase.renderID;
    }
}
