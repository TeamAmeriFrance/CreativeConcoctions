package amerifrance.concoctions.util;

import amerifrance.concoctions.ConcoctionContext;
import amerifrance.concoctions.api.concoctions.Concoction;
import amerifrance.concoctions.api.concoctions.IConcoctionContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.common.util.Constants;

import java.util.Iterator;
import java.util.LinkedList;

public class LivingConcoctions implements IExtendedEntityProperties {

    public static String ID = "activeConcotions";
    public LinkedList<IConcoctionContext> activeConcoctions;

    public LivingConcoctions() {
        activeConcoctions = new LinkedList<IConcoctionContext>();
    }

    public static void create(EntityLivingBase entityLivingBase) {
        entityLivingBase.registerExtendedProperties(ID, new LivingConcoctions());
    }

    public static LivingConcoctions get(EntityLivingBase entityLivingBase) {
        return (LivingConcoctions) entityLivingBase.getExtendedProperties(ID);
    }

    public static LinkedList<IConcoctionContext> getActiveConcotions(EntityLivingBase entityLivingBase) {
        return get(entityLivingBase).getActiveConcoctions();
    }

    @Override
    public void saveNBTData(NBTTagCompound compound) {
        if (!activeConcoctions.isEmpty()) {
            NBTTagList tagList = new NBTTagList();
            Iterator<IConcoctionContext> iterator = activeConcoctions.iterator();
            while (iterator.hasNext()) {
                IConcoctionContext wrapper = iterator.next();
                if (wrapper != null) {
                    NBTTagCompound tagCompound = new NBTTagCompound();
                    wrapper.writeToNBT(tagCompound);
                    tagList.appendTag(tagCompound);
                }
            }
            compound.setTag("activeConcoctions", tagList);
        }
    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {
        NBTTagList tagList = compound.getTagList("activeConcoctions", Constants.NBT.TAG_COMPOUND);
        if (tagList != null) {
            activeConcoctions.clear();
            for (int i = 0; i < tagList.tagCount(); i++) {
                NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
                IConcoctionContext wrapper = new ConcoctionContext(Concoction.readFromNBT(tagCompound));
                wrapper.readFromNBT(tagCompound);
                activeConcoctions.add(wrapper);
            }
        }
    }

    @Override
    public void init(Entity entity, World world) {
        activeConcoctions = new LinkedList<IConcoctionContext>();
    }

    public LinkedList<IConcoctionContext> getActiveConcoctions() {
        return activeConcoctions;
    }
}
