package amerifrance.concoctions.api.ingredients;

import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

public enum IngredientType {
    PLANT,
    MOB_DROP,
    MINERAL,
    PROCESSING_PRODUCT,
    NEUTRAL;

    @Override
    public String toString() {
        return StringUtils.capitalize(name().toLowerCase(Locale.ENGLISH)).replace("_", " ");
    }
}
