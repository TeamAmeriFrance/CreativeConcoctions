package amerifrance.concoctions.api;

import amerifrance.concoctions.objects.Concoction;
import amerifrance.concoctions.objects.ConcoctionContext;
import amerifrance.concoctions.util.LivingConcoctions;
import net.minecraft.entity.EntityLivingBase;

import java.util.Iterator;
import java.util.List;

public class ConcoctionsHelper {

    public static void addConcoction(EntityLivingBase entityLivingBase, String id, int level, int duration) {
        ConcoctionContext wrapper = new ConcoctionContext(ConcoctionsRegistry.getConcoctionForId(id), level, duration);
        addConcoction(entityLivingBase, wrapper);
    }

    public static void addConcoction(EntityLivingBase entityLivingBase, ConcoctionContext wrapper) {
        if (LivingConcoctions.get(entityLivingBase) != null) {
            if (wrapper.getConcoctionLevel() > wrapper.getConcoction().maxLevel) {
                wrapper.setLevel(wrapper.getConcoction().maxLevel);
            }
            LivingConcoctions.getActiveConcotions(entityLivingBase).add(wrapper);
            wrapper.onAdded(entityLivingBase);
        }
    }

    public static void removeConcoction(EntityLivingBase entityLivingBase, String id) {
        removeConcoction(entityLivingBase, getActiveConcoction(entityLivingBase, ConcoctionsRegistry.getConcoctionForId(id)));
    }

    public static void removeConcoction(EntityLivingBase entityLivingBase, ConcoctionContext wrapper) {
        if (wrapper != null) {
            if (LivingConcoctions.get(entityLivingBase) != null && !LivingConcoctions.getActiveConcotions(entityLivingBase).isEmpty()) {
                if (LivingConcoctions.getActiveConcotions(entityLivingBase).contains(wrapper)) {
                    int index = LivingConcoctions.getActiveConcotions(entityLivingBase).indexOf(wrapper);
                    IConcoctionContext remove = LivingConcoctions.getActiveConcotions(entityLivingBase).get(index);
                    remove.setTicksLeft(0);
                }
            }
        }
    }

    public static ConcoctionContext getActiveConcoction(EntityLivingBase entityLivingBase, Concoction concoction) {
        if (LivingConcoctions.get(entityLivingBase) != null && !LivingConcoctions.getActiveConcotions(entityLivingBase).isEmpty()) {
            Iterator<IConcoctionContext> iterator = LivingConcoctions.getActiveConcotions(entityLivingBase).iterator();
            while (iterator.hasNext()) {
                ConcoctionContext wrapper = (ConcoctionContext) iterator.next();
                if (wrapper.getConcoction().equals(concoction)) return wrapper;
            }
        }
        return null;
    }

    public static boolean isConcoctionActive(EntityLivingBase entityLivingBase, Concoction concoction) {
        return getActiveConcoction(entityLivingBase, concoction) != null;
    }

    public static void clearActiveConcoctions(EntityLivingBase entityLivingBase) {
        if (LivingConcoctions.get(entityLivingBase) != null && !LivingConcoctions.getActiveConcotions(entityLivingBase).isEmpty()) {
            Iterator<IConcoctionContext> iterator = LivingConcoctions.getActiveConcotions(entityLivingBase).iterator();
            while (iterator.hasNext()) {
                ConcoctionContext wrapper = (ConcoctionContext) iterator.next();
                iterator.remove();
            }
        }
    }

    public static List<IConcoctionContext> getActiveConcotions(EntityLivingBase entityLivingBase) {
        if (LivingConcoctions.get(entityLivingBase) != null && !LivingConcoctions.getActiveConcotions(entityLivingBase).isEmpty()) {
            return LivingConcoctions.getActiveConcotions(entityLivingBase);
        }
        return null;
    }
}
