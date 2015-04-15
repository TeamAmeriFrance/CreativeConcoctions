package amerifrance.concoctions.api.ingredients;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;

import java.util.Arrays;
import java.util.List;

public class Ingredient {

    public final IngredientType ingredientType;
    public final float unstability;
    public final int potency;
    public final int ticksToBoil;
    private final IngredientProperties[] properties;

    public Ingredient(IngredientType ingredientType, float unstability, int potency, int ticksToBoil, IngredientProperties ... properties) {
        this.ingredientType = ingredientType;
        this.unstability = unstability;
        this.potency = potency;
        this.ticksToBoil = ticksToBoil;
        this.properties = properties;
    }

    public static Ingredient readFromNBT(NBTTagCompound tagCompound) {
        IngredientType type = IngredientType.valueOf(tagCompound.getString("ingredientType"));
        float stability = tagCompound.getFloat("stability");
        int potency = tagCompound.getInteger("potency");
        int ticksToBoil = tagCompound.getInteger("ticksToBoil");

        IngredientProperties[] properties = null;
        NBTTagList tagList = tagCompound.getTagList("properties", Constants.NBT.TAG_COMPOUND);
        if (tagList != null) {
            properties = new IngredientProperties[tagList.tagCount()];
            for (int i = 0; i < tagList.tagCount(); i++) {
                NBTTagCompound tag = tagList.getCompoundTagAt(i);
                properties[i] = IngredientProperties.valueOf(tag.getString("properties"));
            }
        }

        return new Ingredient(type, stability, potency, ticksToBoil, properties);
    }

    public void writeToNBT(NBTTagCompound tagCompound) {
        tagCompound.setString("ingredientType", ingredientType.name());
        tagCompound.setFloat("unstability", unstability);
        tagCompound.setInteger("potency", potency);
        tagCompound.setInteger("ticksToBoil", ticksToBoil);

        NBTTagList tagList = new NBTTagList();
        for (IngredientProperties property : properties) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("property", property.name());
            tagList.appendTag(tag);
        }
        tagCompound.setTag("properties", tagList);
    }

    public List<IngredientProperties> getPropertiesList() {
        return Arrays.asList(properties);
    }

    public IngredientProperties[] getProperties() {
        return Arrays.copyOf(properties, properties.length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        if (potency != that.potency) return false;
        if (Float.compare(that.unstability, unstability) != 0) return false;
        if (ingredientType != that.ingredientType) return false;
        if (!Arrays.equals(properties, that.properties)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = ingredientType != null ? ingredientType.hashCode() : 0;
        result = 31 * result + (unstability != +0.0f ? Float.floatToIntBits(unstability) : 0);
        result = 31 * result + potency;
        result = 31 * result + (properties != null ? Arrays.hashCode(properties) : 0);
        return result;
    }
}
