package amerifrance.concoctions.objects;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;

public class ConcoctionWrapper {

    private Concoction concoction;
    private int concoctionLevel;
    private int ticksLeft;
    private int initialDuration;

    public ConcoctionWrapper(Concoction concoction, int concoctionLevel, int initialDuration) {
        this.concoction = concoction;
        this.concoctionLevel = concoctionLevel;
        this.initialDuration = initialDuration;
        this.ticksLeft = initialDuration;
    }

    private ConcoctionWrapper(Concoction concoction, int concoctionLevel, int ticksLeft, int initialDuration) {
        this.concoction = concoction;
        this.concoctionLevel = concoctionLevel;
        this.ticksLeft = ticksLeft;
        this.initialDuration = initialDuration;
    }

    public static ConcoctionWrapper readFromNBT(NBTTagCompound tagCompound) {
        Concoction concoction = Concoction.readFromNBT(tagCompound);
        int level = tagCompound.getInteger("concoctionLevel");
        int ticksLeft = tagCompound.getInteger("ticksLeft");
        int initialDuration = tagCompound.getInteger("initialDuration");
        return new ConcoctionWrapper(concoction, level, ticksLeft, initialDuration);
    }

    public void onUpdate(EntityLivingBase entityLivingBase) {
        this.concoction.applyEffect(entityLivingBase, this);
    }

    public void onAdded(EntityLivingBase entityLivingBase) {
        this.concoction.onEffectAdded(entityLivingBase, this);
    }

    public void onRemoved(EntityLivingBase entityLivingBase) {
        this.concoction.onEffectRemoved(entityLivingBase, this);
    }

    public NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
        tagCompound.setInteger("concoctionLevel", concoctionLevel);
        tagCompound.setInteger("ticksLeft", ticksLeft);
        tagCompound.setInteger("initialDuration", initialDuration);
        concoction.writeToNBT(tagCompound);
        return tagCompound;
    }

    public Concoction getConcoction() {
        return concoction;
    }

    public int getConcoctionLevel() {
        return concoctionLevel;
    }

    public int getTicksLeft() {
        return ticksLeft;
    }

    public int getInitialDuration() {
        return initialDuration;
    }

    public void decrementTicksLeft() {
        ticksLeft--;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConcoctionWrapper wrapper = (ConcoctionWrapper) o;

        if (concoctionLevel != wrapper.concoctionLevel) return false;
        if (initialDuration != wrapper.initialDuration) return false;
        if (concoction != null ? !concoction.equals(wrapper.concoction) : wrapper.concoction != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = concoction != null ? concoction.hashCode() : 0;
        result = 31 * result + concoctionLevel;
        result = 31 * result + initialDuration;
        return result;
    }

    @Override
    public String toString() {
        return "ConcoctionWrapper{" +
                "concoction=" + concoction +
                ", concoctionLevel=" + concoctionLevel +
                ", ticksLeft=" + ticksLeft +
                ", initialDuration=" + initialDuration +
                '}';
    }
}
