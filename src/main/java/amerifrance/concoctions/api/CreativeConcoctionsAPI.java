package amerifrance.concoctions.api;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class CreativeConcoctionsAPI {

    public static NBTTagCompound checkAndSetCompound(ItemStack stack) {
        if (!stack.hasTagCompound()) {
            NBTTagCompound tag = new NBTTagCompound();
            stack.setTagCompound(tag);
            return tag;
        }
        return null;
    }

    public static int dividingSafeInt(int n) {
        if (n == 0) return n + 1;
        else return n;
    }

    public static int average(int... ints) {
        int sum = 0;
        for (int i : ints) sum += i;
        return sum / ints.length;
    }
}
