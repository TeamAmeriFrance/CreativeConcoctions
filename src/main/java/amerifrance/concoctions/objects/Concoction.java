package amerifrance.concoctions.objects;

import amerifrance.concoctions.util.ConcoctionsRegistry;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;

import java.awt.*;

public class Concoction {

    public String concoctionName;
    public int maximumLevel;
    public Color concoctionColor;
    private ConcotionType concotionType;

    private Concoction() {
    }

    public Concoction(String concoctionName, int maximumLevel, Color concoctionColor) {
        this.concoctionName = concoctionName;
        this.maximumLevel = maximumLevel;
        this.concoctionColor = concoctionColor;
        this.concotionType = ConcotionType.NEUTRAL;
    }

    public Concoction(String concoctionName, int maximumLevel, Color concoctionColor, ConcotionType concotionType) {
        this.concoctionName = concoctionName;
        this.maximumLevel = maximumLevel;
        this.concoctionColor = concoctionColor;
        this.concotionType = concotionType;
    }

    public static Concoction readFromNBT(NBTTagCompound tagCompound) {
        String name = tagCompound.getString("concoctionName");
        int level = tagCompound.getInteger("maximumLevel");
        Color color = new Color(tagCompound.getInteger("concoctionColor"));
        ConcotionType type = ConcotionType.valueOf(tagCompound.getString("concotionType"));
        String id = tagCompound.getString("id");

        Concoction concoction = null;
        Class concoctionClass = ConcoctionsRegistry.getConcoctionForId(id);
        if (concoctionClass != null) try {
            concoction = (Concoction) concoctionClass.newInstance();
            concoction.concoctionName = name;
            concoction.maximumLevel = level;
            concoction.concoctionColor = color;
            concoction.concotionType = type;
            return concoction;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return new Concoction(name, level, color, type);
    }

    public void applyEffect(EntityLivingBase entityLivingBase, ConcoctionWrapper wrapper) {
    }

    public void onEffectAdded(EntityLivingBase entityLivingBase, ConcoctionWrapper wrapper) {
    }

    public void onEffectRemoved(EntityLivingBase entityLivingBase, ConcoctionWrapper wrapper) {
    }

    public void writeToNBT(NBTTagCompound tagCompound) {
        tagCompound.setString("concoctionName", concoctionName);
        tagCompound.setInteger("maximumLevel", maximumLevel);
        tagCompound.setInteger("concoctionColor", concoctionColor.getRGB());
        tagCompound.setString("concotionType", concotionType.name());
        tagCompound.setString("id", ConcoctionsRegistry.getIdForConcoction(this.getClass()));
    }

    public ConcotionType getConcotionType() {
        return concotionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Concoction that = (Concoction) o;

        if (maximumLevel != that.maximumLevel) return false;
        if (concoctionColor != null ? !concoctionColor.equals(that.concoctionColor) : that.concoctionColor != null)
            return false;
        if (concoctionName != null ? !concoctionName.equals(that.concoctionName) : that.concoctionName != null)
            return false;
        if (concotionType != that.concotionType) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = concoctionName != null ? concoctionName.hashCode() : 0;
        result = 31 * result + maximumLevel;
        result = 31 * result + (concoctionColor != null ? concoctionColor.hashCode() : 0);
        result = 31 * result + (concotionType != null ? concotionType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Concoction{" +
                "concoctionName='" + concoctionName +
                ", maximumLevel=" + maximumLevel +
                ", concoctionColor=" + concoctionColor +
                ", concotionType=" + concotionType +
                "}";
    }
}
