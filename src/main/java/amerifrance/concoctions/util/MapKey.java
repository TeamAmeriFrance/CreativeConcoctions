package amerifrance.concoctions.util;

import net.minecraft.item.ItemStack;

public class MapKey {
    public final ItemStack stack;
    public int hashcode;

    public MapKey(ItemStack stack) {
        this.hashcode = stack.getItem().hashCode() ^ stack.getItemDamage();
        this.stack = stack;
    }

    public static MapKey getKey(ItemStack stack) {
        if (stack == null || stack.getItem() == null) return null;
        return new MapKey(stack);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof MapKey) return hashcode == ((MapKey) o).hashcode;
        return false;
    }

    @Override
    public int hashCode() {
        return hashcode;
    }
}