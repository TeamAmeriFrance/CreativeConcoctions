package amerifrance.concoctions.objects;

import amerifrance.concoctions.api.IConcoctionContext;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;

public class ConcoctionContext implements IConcoctionContext {

    private final Concoction concoction;

    private int level;
    private int duration;

    private int ticksLeft;

    public ConcoctionContext(Concoction concoction) {
        this(concoction, 0, 0);
    }

    public ConcoctionContext(Concoction concoction, int level, int initialDuration) {
        this.concoction = concoction;
        this.level = level;
        this.duration = initialDuration;
        this.ticksLeft = initialDuration;
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        this.level = tagCompound.getInteger("level");
        this.ticksLeft = tagCompound.getInteger("ticksLeft");
        this.duration = tagCompound.getInteger("duration");
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        tagCompound.setInteger("level", level);
        tagCompound.setInteger("ticksLeft", ticksLeft);
        tagCompound.setInteger("duration", duration);
        concoction.writeToNBT(tagCompound);
    }

    @Override
    public void onUpdate(EntityLivingBase entityLivingBase) {
        this.concoction.updateEffect(entityLivingBase, this);
        this.ticksLeft--;
    }

    @Override
    public void onAdded(EntityLivingBase entityLivingBase) {
        this.concoction.onEffectAdded(entityLivingBase, this);
    }

    @Override
    public void onRemoved(EntityLivingBase entityLivingBase) {
        this.concoction.onEffectRemoved(entityLivingBase, this);
    }

    @Override
    public Concoction getConcoction() {
        return concoction;
    }

    @Override
    public int getConcoctionLevel() {
        return level;
    }

    @Override
    public int getTicksLeft() {
        return ticksLeft;
    }

    @Override
    public void setTicksLeft(int ticksLeft) {
        this.ticksLeft = ticksLeft;
    }

    @Override
    public int getInitialDuration() {
        return duration;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ConcoctionContext wrapper = (ConcoctionContext) o;

        if (level != wrapper.level)
            return false;
        if (duration != wrapper.duration)
            return false;
        if (concoction != null ? !concoction.equals(wrapper.concoction) : wrapper.concoction != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = concoction != null ? concoction.hashCode() : 0;
        result = 31 * result + level;
        result = 31 * result + duration;
        return result;
    }

    @Override
    public String toString() {
        return "ConcoctionWrapper{" + "concoction=" + concoction + ", concoctionLevel=" + level + ", ticksLeft=" + ticksLeft + ", initialDuration=" + duration + '}';
    }
}
