package amerifrance.concoctions.client.render;

import amerifrance.concoctions.blocks.BlockCauldronBase;
import amerifrance.concoctions.registry.BlocksRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.IItemRenderer;

public class RenderCauldron implements ISimpleBlockRenderingHandler, IItemRenderer {

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
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
        return true;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    @Override
    public int getRenderId() {
        return BlockCauldronBase.renderID;
    }

    @Override
    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return type.ordinal() < IItemRenderer.ItemRenderType.FIRST_PERSON_MAP.ordinal();
    }

    @Override
    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
    }
}
