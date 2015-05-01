package amerifrance.concoctions.api;

import amerifrance.concoctions.api.concoctions.Concoction;
import amerifrance.concoctions.api.registry.ConcoctionsRegistry;
import amerifrance.concoctions.api.util.NBTTags;
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

    public static Concoction getConcoction(ItemStack stack) {
        checkAndSetCompound(stack);
        return ConcoctionsRegistry.getConcoctionForId(stack.stackTagCompound.getString(NBTTags.ID_TAG));
    }

    public static int getDuration(ItemStack stack) {
        checkAndSetCompound(stack);
        return stack.stackTagCompound.getInteger(NBTTags.DURATION_TAG);
    }

    public static int getLevel(ItemStack stack) {
        checkAndSetCompound(stack);
        return stack.stackTagCompound.getInteger(NBTTags.LEVEL_TAG);
    }

    public static int getUsesLeft(ItemStack stack) {
        checkAndSetCompound(stack);
        return stack.stackTagCompound.getInteger(NBTTags.USES_LEFT_TAG);
    }

    public static void setConcoction(ItemStack stack, Concoction concoction) {
        checkAndSetCompound(stack);
        stack.stackTagCompound.setString(NBTTags.ID_TAG, ConcoctionsRegistry.getIdForConcoction(concoction));
    }

    public static void setDuration(ItemStack stack, int duration) {
        checkAndSetCompound(stack);
        stack.stackTagCompound.setInteger(NBTTags.DURATION_TAG, duration);
    }

    public static void setLevel(ItemStack stack, int level) {
        checkAndSetCompound(stack);
        stack.stackTagCompound.setInteger(NBTTags.LEVEL_TAG, level);
    }

    public static void setUsesLeft(ItemStack stack, int usesLeft) {
        checkAndSetCompound(stack);
        stack.stackTagCompound.setInteger(NBTTags.USES_LEFT_TAG, usesLeft);
    }
}
