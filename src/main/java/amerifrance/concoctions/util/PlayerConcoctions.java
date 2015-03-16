package amerifrance.concoctions.util;

import amerifrance.concoctions.objects.ConcoctionWrapper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class PlayerConcoctions implements IExtendedEntityProperties {

    public static String ID = "CreativeConcotions:ActiveConcotions";
    private List<ConcoctionWrapper> activeConcoctions;

    public PlayerConcoctions() {
        activeConcoctions = new ArrayList<ConcoctionWrapper>();
    }

    public static void create(EntityLivingBase entityLivingBase) {
        entityLivingBase.registerExtendedProperties(ID, new PlayerConcoctions());
    }

    public static PlayerConcoctions get(EntityLivingBase entityLivingBase) {
        return (PlayerConcoctions) entityLivingBase.getExtendedProperties(ID);
    }

    public static List<ConcoctionWrapper> getActiveConcotions(EntityLivingBase entityLivingBase) {
        return get(entityLivingBase).activeConcoctions;
    }

    @Override
    public void saveNBTData(NBTTagCompound compound) {
        if (!activeConcoctions.isEmpty()) {
            NBTTagList tagList = new NBTTagList();
            for (ConcoctionWrapper wrapper : activeConcoctions) {
                if (wrapper != null) {
                    NBTTagCompound tagCompound = new NBTTagCompound();
                    tagList.appendTag(wrapper.writeToNBT(tagCompound));
                }
            }
            compound.setTag("activeConcoctions", tagList);
        }
    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {
        NBTTagList tagList = compound.getTagList("activeConcoctions", Constants.NBT.TAG_COMPOUND);
        if (tagList != null) {
            List<ConcoctionWrapper> list = new ArrayList<ConcoctionWrapper>();
            for (int i = 0; i < tagList.tagCount(); i++) {
                NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
                list.add(ConcoctionWrapper.readFromNBT(tagCompound));
            }
            activeConcoctions = list;
        }
    }

    @Override
    public void init(Entity entity, World world) {
        activeConcoctions = new ArrayList<ConcoctionWrapper>();
    }

    public List<ConcoctionWrapper> getActiveConcoctions() {
        return activeConcoctions;
    }
}
