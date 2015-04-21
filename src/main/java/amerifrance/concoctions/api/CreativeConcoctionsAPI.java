package amerifrance.concoctions.api;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.Random;

public class CreativeConcoctionsAPI {

    public static String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    public static Random random = new Random();

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

    public static String randomString(int length) {
        String returnString = "";
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            returnString += characters.charAt(index);
        }
        return returnString;
    }
}
