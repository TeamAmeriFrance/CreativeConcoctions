package amerifrance.concoctions.api.ingredients;

import net.minecraft.util.StatCollector;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

public enum IngredientType {
    MINERAL,
    MOB_DROP,
    NEUTRAL,
    PLANT,
    PROCESSING_PRODUCT;

    @Override
    public String toString() {
        return StringUtils.capitalize(name().toLowerCase(Locale.ENGLISH)).replace("_", " ");
    }

    public String getLocalizedString() {
        return StatCollector.translateToLocal("ingredient.type." + name().toLowerCase());
    }
}
