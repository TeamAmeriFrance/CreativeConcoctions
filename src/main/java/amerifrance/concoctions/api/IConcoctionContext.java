package amerifrance.concoctions.api;

import amerifrance.concoctions.objects.Concoction;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;

public interface IConcoctionContext {

    void readFromNBT(NBTTagCompound tagCompound);

    void writeToNBT(NBTTagCompound tagCompound);

    void onUpdate(EntityLivingBase entityLivingBase);

    void onAdded(EntityLivingBase entityLivingBase);

    void onRemoved(EntityLivingBase entityLivingBase);

    Concoction getConcoction();

    int getConcoctionLevel();

    int getTicksLeft();

    void setTicksLeft(int ticksLeft);

    int getInitialDuration();

    void setLevel(int level);
}
