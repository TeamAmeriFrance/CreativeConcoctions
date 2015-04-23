package amerifrance.concoctions.api.ingredients;

import net.minecraft.util.StatCollector;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

public enum IngredientProperty {

    AIR,
    ATTACK,
    CLEANSING,
    CONFUSION,
    CURATIVE,
    DAMAGE,
    DEMONIC,
    EARTH,
    ENERGY,
    EVIL,
    EXPLOSIVE,
    FIRE,
    FLIGHT,
    GRAVITY,
    HEALING,
    INVISIBILITY,
    MAGIC,
    MORPHING,
    NOURISHMENT,
    POISON,
    PROTECTION,
    REGENERATION,
    SLOWING_DOWN,
    SPEED,
    STRENGTH,
    SWIMMING,
    WATER_BREATHING,
    YOUTH,

    //These can never be part of any recipe
    CATALYST,
    COOLANT,
    STABILIZER,
    UNSTABLE,

    //This one is a part of every single recipe
    WATER;

    @Override
    public String toString() {
        return StringUtils.capitalize(name().toLowerCase(Locale.ENGLISH)).replace("_", " ");
    }

    public String getLocalizedString() {
        return StatCollector.translateToLocal("ingredient.property." + name().toLowerCase());
    }
}