package amerifrance.concoctions.util;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class InventoryHelper {

    public static ItemStackAndSlot getItemFromInventory(Item item, IInventory iInventory) {
        for (int i = 0; i < iInventory.getSizeInventory(); i++) {
            if (iInventory.getStackInSlot(i) != null && iInventory.getStackInSlot(i).getItem() == item)
                return new ItemStackAndSlot(iInventory.getStackInSlot(i), i);
        }
        return null;
    }

    public static ItemStackAndSlot getItemFromInventory(Item item, ItemStack[] itemStacks) {
        for (int i = 0; i < itemStacks.length; i++) {
            if (itemStacks[i] != null && itemStacks[i].getItem() == item)
                return new ItemStackAndSlot(itemStacks[i], i);
        }
        return null;
    }

    public static class ItemStackAndSlot {
        public ItemStack stack;
        public int slot;

        public ItemStackAndSlot(ItemStack stack, int slot) {
            this.stack = stack;
            this.slot = slot;
        }
    }
}
