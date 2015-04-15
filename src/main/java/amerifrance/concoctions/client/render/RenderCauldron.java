package amerifrance.concoctions.client.render;

import amerifrance.concoctions.api.registry.ConcoctionRecipes;
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

import java.awt.*;

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
        if (cauldronBase.getIngredientCapacity() != 0 && cauldronBase.cauldronContent.size() != 0) {
            if (!cauldronBase.checkRecipe()) {
                renderer.renderFaceYPos(block, x, y - 1.0F + cauldronBase.getLiquidHeightForRender(), z, water);
            } else {
                renderFaceYPosWithColor(renderer, block, x, y - 1.0F + cauldronBase.getLiquidHeightForRender(), z, water, ConcoctionRecipes.getConcoctionForIngredients(cauldronBase.cauldronContent).color);
            }
        }
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

    private void renderFaceYPosWithColor(RenderBlocks renderer, Block block, double x, double y, double z, IIcon icon, Color color) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.setColorRGBA(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        if (renderer.hasOverrideBlockTexture()) {
            icon = renderer.overrideBlockTexture;
        }
        double d3 = (double) icon.getInterpolatedU(renderer.renderMinX * 16.0D);
        double d4 = (double) icon.getInterpolatedU(renderer.renderMaxX * 16.0D);
        double d5 = (double) icon.getInterpolatedV(renderer.renderMinZ * 16.0D);
        double d6 = (double) icon.getInterpolatedV(renderer.renderMaxZ * 16.0D);
        if (renderer.renderMinX < 0.0D || renderer.renderMaxX > 1.0D) {
            d3 = (double) icon.getMinU();
            d4 = (double) icon.getMaxU();
        }
        if (renderer.renderMinZ < 0.0D || renderer.renderMaxZ > 1.0D) {
            d5 = (double) icon.getMinV();
            d6 = (double) icon.getMaxV();
        }
        double d7 = d4;
        double d8 = d3;
        double d9 = d5;
        double d10 = d6;
        if (renderer.uvRotateTop == 1) {
            d3 = (double) icon.getInterpolatedU(renderer.renderMinZ * 16.0D);
            d5 = (double) icon.getInterpolatedV(16.0D - renderer.renderMaxX * 16.0D);
            d4 = (double) icon.getInterpolatedU(renderer.renderMaxZ * 16.0D);
            d6 = (double) icon.getInterpolatedV(16.0D - renderer.renderMinX * 16.0D);
            d9 = d5;
            d10 = d6;
            d7 = d3;
            d8 = d4;
            d5 = d6;
            d6 = d9;
        } else if (renderer.uvRotateTop == 2) {
            d3 = (double) icon.getInterpolatedU(16.0D - renderer.renderMaxZ * 16.0D);
            d5 = (double) icon.getInterpolatedV(renderer.renderMinX * 16.0D);
            d4 = (double) icon.getInterpolatedU(16.0D - renderer.renderMinZ * 16.0D);
            d6 = (double) icon.getInterpolatedV(renderer.renderMaxX * 16.0D);
            d7 = d4;
            d8 = d3;
            d3 = d4;
            d4 = d8;
            d9 = d6;
            d10 = d5;
        } else if (renderer.uvRotateTop == 3) {
            d3 = (double) icon.getInterpolatedU(16.0D - renderer.renderMinX * 16.0D);
            d4 = (double) icon.getInterpolatedU(16.0D - renderer.renderMaxX * 16.0D);
            d5 = (double) icon.getInterpolatedV(16.0D - renderer.renderMinZ * 16.0D);
            d6 = (double) icon.getInterpolatedV(16.0D - renderer.renderMaxZ * 16.0D);
            d7 = d4;
            d8 = d3;
            d9 = d5;
            d10 = d6;
        }
        double d11 = x + renderer.renderMinX;
        double d12 = x + renderer.renderMaxX;
        double d13 = y + renderer.renderMaxY;
        double d14 = z + renderer.renderMinZ;
        double d15 = z + renderer.renderMaxZ;
        if (renderer.renderFromInside) {
            d11 = x + renderer.renderMaxX;
            d12 = x + renderer.renderMinX;
        }
        if (renderer.enableAO) {
            tessellator.setColorOpaque_F(renderer.colorRedTopLeft, renderer.colorGreenTopLeft, renderer.colorBlueTopLeft);
            tessellator.setBrightness(renderer.brightnessTopLeft);
            tessellator.addVertexWithUV(d12, d13, d15, d4, d6);
            tessellator.setColorOpaque_F(renderer.colorRedBottomLeft, renderer.colorGreenBottomLeft, renderer.colorBlueBottomLeft);
            tessellator.setBrightness(renderer.brightnessBottomLeft);
            tessellator.addVertexWithUV(d12, d13, d14, d7, d9);
            tessellator.setColorOpaque_F(renderer.colorRedBottomRight, renderer.colorGreenBottomRight, renderer.colorBlueBottomRight);
            tessellator.setBrightness(renderer.brightnessBottomRight);
            tessellator.addVertexWithUV(d11, d13, d14, d3, d5);
            tessellator.setColorOpaque_F(renderer.colorRedTopRight, renderer.colorGreenTopRight, renderer.colorBlueTopRight);
            tessellator.setBrightness(renderer.brightnessTopRight);
            tessellator.addVertexWithUV(d11, d13, d15, d8, d10);
        } else {
            tessellator.addVertexWithUV(d12, d13, d15, d4, d6);
            tessellator.addVertexWithUV(d12, d13, d14, d7, d9);
            tessellator.addVertexWithUV(d11, d13, d14, d3, d5);
            tessellator.addVertexWithUV(d11, d13, d15, d8, d10);
        }
    }
}
