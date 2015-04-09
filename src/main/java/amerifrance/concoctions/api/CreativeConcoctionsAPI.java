package amerifrance.concoctions.api;

import amerifrance.concoctions.api.concoctions.Concoction;
import amerifrance.concoctions.api.registry.ConcoctionsRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class CreativeConcoctionsAPI {

    public static String CONCOCTION_ID_TAG = "ConcoctionID";
    public static String CONCOCTION_LEVEL_TAG = "ConcoctionLevel";
    public static String CONCOCTION_DURATION_TAG = "ConcoctionDuration";

    public static NBTTagCompound checkAndSetCompound(ItemStack stack) {
        if (!stack.hasTagCompound()) {
            NBTTagCompound tag = new NBTTagCompound();
            stack.setTagCompound(tag);
            return tag;
        }
        return null;
    }

    public static Concoction getConcoction(ItemStack stack) {
        checkAndSetCompound(stack);
        return ConcoctionsRegistry.getConcoctionForId(stack.stackTagCompound.getString(CONCOCTION_ID_TAG));
    }

    public static int getLevel(ItemStack stack) {
        checkAndSetCompound(stack);
        return stack.stackTagCompound.getInteger(CONCOCTION_LEVEL_TAG);
    }

    public static int getDuration(ItemStack stack) {
        checkAndSetCompound(stack);
        return stack.stackTagCompound.getInteger(CONCOCTION_DURATION_TAG);
    }

    public static void setConcoctionContext(ItemStack stack, Concoction concoction, int level, int duration) {
        setConcoction(stack, concoction);
        setLevel(stack, level);
        setDuration(stack, duration);
    }

    public static void setConcoction(ItemStack stack, Concoction concoction) {
        checkAndSetCompound(stack);
        stack.stackTagCompound.setString(CONCOCTION_ID_TAG, ConcoctionsRegistry.getIdForConcoction(concoction));
    }

    public static void setLevel(ItemStack stack, int level) {
        checkAndSetCompound(stack);
        stack.stackTagCompound.setInteger(CONCOCTION_LEVEL_TAG, level);
    }

    public static void setDuration(ItemStack stack, int duration) {
        checkAndSetCompound(stack);
        stack.stackTagCompound.setInteger(CONCOCTION_DURATION_TAG, duration);
    }

    public static int dividingSafeInt(int n) {
        if (n == 0) return n + 1;
        else return n;
    }
}
