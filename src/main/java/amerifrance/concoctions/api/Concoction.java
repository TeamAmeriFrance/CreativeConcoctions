package amerifrance.concoctions.api;

import java.awt.Color;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;

/**
 * An immutable representation of a cocoction's effects. This is not used for
 * per-effect data, but rather holds the concoction's initial state, from which
 * a {@link amerifrance.concoctions.IConcoctionContext} is created to store data.
 */
public class Concoction {
    public final String name;
    public final int maxLevel;
    public final Color color;
    public final ConcoctionType type;

    public Concoction(String name, int maxLevel, Color color) {
        this(name, maxLevel, color, ConcoctionType.NEUTRAL);
    }

    public Concoction(String name, int maxLevel, Color color, ConcoctionType type) {
        this.name = name;
        this.maxLevel = maxLevel;
        this.color = color;
        this.type = type;
    }

    public static Concoction readFromNBT(NBTTagCompound tagCompound) {
        String id = tagCompound.getString("id");
        return ConcoctionsRegistry.getConcoctionForId(id);
    }

    public final void writeToNBT(NBTTagCompound tagCompound) {
        tagCompound.setString("id", ConcoctionsRegistry.getIdForConcoction(this));
    }

    /**
     * Called once every tick per entity this concoction is on.
     *
     * @param livingBase The entity with the concoction.
     * @param ctx              Context information.
     */
    public void updateEffect(EntityLivingBase livingBase, IConcoctionContext ctx) {
        ;
    }

    /**
     * Called when this concoction is added to an entity.
     *
     * @param livingBase The entity with the concoction.
     * @param ctx              Context information.
     */
    public void onEffectAdded(EntityLivingBase livingBase, IConcoctionContext ctx) {
        ;
    }

    /**
     * Called when this concoction is removed from an entity.
     *
     * @param livingBase The entity with the concoction.
     * @param ctx              Context information.
     */
    public void onEffectRemoved(EntityLivingBase livingBase, IConcoctionContext ctx) {
        ;
    }

    public ConcoctionType getConcotionType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Concoction that = (Concoction) o;

        if (maxLevel != that.maxLevel)
            return false;
        if (color != null ? !color.equals(that.color) : that.color != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null)
            return false;
        if (type != that.type)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + maxLevel;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Concoction{" + "concoctionName='" + name + ", maximumLevel=" + maxLevel + ", concoctionColor=" + color + ", concotionType=" + type + "}";
    }
}
