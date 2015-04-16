package amerifrance.concoctions.items.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockCauldrons extends ItemBlock {

    public ItemBlockCauldrons(Block block) {
        super(block);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int par1) {
        return par1;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName() + "." + String.valueOf(stack.getItemDamage());
    }
}
