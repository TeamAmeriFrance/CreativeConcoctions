package amerifrance.concoctions.api;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import amerifrance.concoctions.objects.Concoction;

public interface IConcoctionContext {

	void readFromNBT(NBTTagCompound tagCompound);

	void writeToNBT(NBTTagCompound tagCompound);

	void onUpdate(EntityLivingBase entityLivingBase);

	void onAdded(EntityLivingBase entityLivingBase);

	void onRemoved(EntityLivingBase entityLivingBase);

	Concoction getConcoction();

	int getConcoctionLevel();

	int getTicksLeft();

	int getInitialDuration();

	void setTicksLeft(int ticksLeft);
}
