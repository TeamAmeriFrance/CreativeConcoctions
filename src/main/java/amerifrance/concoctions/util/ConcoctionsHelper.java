package amerifrance.concoctions.util;

import amerifrance.concoctions.objects.Concoction;
import amerifrance.concoctions.objects.ConcoctionWrapper;
import net.minecraft.entity.EntityLivingBase;

import java.util.List;

public class ConcoctionsHelper {

    public static void addConcoction(EntityLivingBase entityLivingBase, Concoction concoction, int level, int duration) {
        ConcoctionWrapper wrapper = new ConcoctionWrapper(concoction, level, duration);
        addConcoction(entityLivingBase, wrapper);
    }

    public static void addConcoction(EntityLivingBase entityLivingBase, ConcoctionWrapper wrapper) {
        if (LivingConcoctions.get(entityLivingBase) != null) {
            LivingConcoctions.getActiveConcotions(entityLivingBase).add(wrapper);
            wrapper.onAdded(entityLivingBase);
        }
    }

    public static void removeConcoction(EntityLivingBase entityLivingBase, Concoction concoction, int level, int duration) {
        ConcoctionWrapper wrapper = new ConcoctionWrapper(concoction, level, duration);
        removeConcoction(entityLivingBase, wrapper);
    }

    public static void removeConcoction(EntityLivingBase entityLivingBase, ConcoctionWrapper wrapper) {
        wrapper.getConcoction().onEffectRemoved(entityLivingBase, wrapper);
        if (LivingConcoctions.get(entityLivingBase) != null && !LivingConcoctions.getActiveConcotions(entityLivingBase).isEmpty()) {
            if (LivingConcoctions.getActiveConcotions(entityLivingBase).contains(wrapper)) {
                wrapper.onRemoved(entityLivingBase);
                LivingConcoctions.getActiveConcotions(entityLivingBase).remove(wrapper);
            }
        }
    }

    public static ConcoctionWrapper getActiveConcoctionWrapper(EntityLivingBase entityLivingBase, Concoction concoction) {
        if (LivingConcoctions.get(entityLivingBase) != null && !LivingConcoctions.getActiveConcotions(entityLivingBase).isEmpty()) {
            for (ConcoctionWrapper wrapper : LivingConcoctions.getActiveConcotions(entityLivingBase)) {
                if (wrapper.getConcoction().equals(concoction)) {
                    return wrapper;
                }
            }
        }
        return null;
    }

    public static boolean isConcoctionActive(EntityLivingBase entityLivingBase, Concoction concoction) {
        return getActiveConcoctionWrapper(entityLivingBase, concoction) != null;
    }

    public static void clearActiveConcoctions(EntityLivingBase entityLivingBase) {
        if (LivingConcoctions.get(entityLivingBase) != null && !LivingConcoctions.getActiveConcotions(entityLivingBase).isEmpty()) {
            LivingConcoctions.getActiveConcotions(entityLivingBase).clear();
        }
    }

    public static List<ConcoctionWrapper> getActiveConcotions(EntityLivingBase entityLivingBase) {
        if (LivingConcoctions.get(entityLivingBase) != null && !LivingConcoctions.getActiveConcotions(entityLivingBase).isEmpty()) {
            return LivingConcoctions.getActiveConcotions(entityLivingBase);
        }
        return null;
    }
}
