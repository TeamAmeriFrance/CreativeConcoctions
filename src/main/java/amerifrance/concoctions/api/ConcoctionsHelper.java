package amerifrance.concoctions.api;

import amerifrance.concoctions.objects.Concoction;
import amerifrance.concoctions.objects.ConcoctionContext;
import amerifrance.concoctions.util.LivingConcoctions;
import net.minecraft.entity.EntityLivingBase;

import java.util.Iterator;
import java.util.List;

public class ConcoctionsHelper {

    public static void addConcoction(EntityLivingBase livingBase, String id, int level, int duration) {
        ConcoctionContext wrapper = new ConcoctionContext(ConcoctionsRegistry.getConcoctionForId(id), level, duration);
        addConcoction(livingBase, wrapper);
    }

    public static void addConcoction(EntityLivingBase livingBase, Concoction concoction, int level, int duration) {
        ConcoctionContext wrapper = new ConcoctionContext(concoction, level, duration);
        addConcoction(livingBase, wrapper);
    }

    public static void addConcoction(EntityLivingBase livingBase, ConcoctionContext wrapper) {
        if (LivingConcoctions.get(livingBase) != null) {
            if (wrapper.getConcoctionLevel() > wrapper.getConcoction().maxLevel) {
                wrapper.setLevel(wrapper.getConcoction().maxLevel);
            }
            LivingConcoctions.getActiveConcotions(livingBase).add(wrapper);
            wrapper.onAdded(livingBase);
        }
    }

    public static void removeConcoction(EntityLivingBase livingBase, String id) {
        removeConcoction(livingBase, getActiveConcoction(livingBase, ConcoctionsRegistry.getConcoctionForId(id)));
    }

    public static void removeConcoction(EntityLivingBase livingBase, Concoction concoction) {
        removeConcoction(livingBase, getActiveConcoction(livingBase, concoction));
    }

    public static void removeConcoction(EntityLivingBase livingBase, ConcoctionContext wrapper) {
        if (wrapper != null) {
            if (LivingConcoctions.get(livingBase) != null && !LivingConcoctions.getActiveConcotions(livingBase).isEmpty()) {
                if (LivingConcoctions.getActiveConcotions(livingBase).contains(wrapper)) {
                    int index = LivingConcoctions.getActiveConcotions(livingBase).indexOf(wrapper);
                    IConcoctionContext remove = LivingConcoctions.getActiveConcotions(livingBase).get(index);
                    remove.setTicksLeft(0);
                }
            }
        }
    }

    public static ConcoctionContext getActiveConcoction(EntityLivingBase livingBase, String id) {
        return getActiveConcoction(livingBase, ConcoctionsRegistry.getConcoctionForId(id));
    }

    public static ConcoctionContext getActiveConcoction(EntityLivingBase livingBase, Concoction concoction) {
        if (LivingConcoctions.get(livingBase) != null && !LivingConcoctions.getActiveConcotions(livingBase).isEmpty()) {
            Iterator<IConcoctionContext> iterator = LivingConcoctions.getActiveConcotions(livingBase).iterator();
            while (iterator.hasNext()) {
                ConcoctionContext wrapper = (ConcoctionContext) iterator.next();
                if (wrapper.getConcoction().equals(concoction)) return wrapper;
            }
        }
        return null;
    }

    public static boolean isConcoctionActive(EntityLivingBase livingBase, String id) {
        return getActiveConcoction(livingBase, id) != null;
    }

    public static boolean isConcoctionActive(EntityLivingBase livingBase, Concoction concoction) {
        return getActiveConcoction(livingBase, concoction) != null;
    }

    public static void clearActiveConcoctions(EntityLivingBase livingBase) {
        if (LivingConcoctions.get(livingBase) != null && !LivingConcoctions.getActiveConcotions(livingBase).isEmpty()) {
            Iterator<IConcoctionContext> iterator = LivingConcoctions.getActiveConcotions(livingBase).iterator();
            while (iterator.hasNext()) {
                ConcoctionContext wrapper = (ConcoctionContext) iterator.next();
                iterator.remove();
            }
        }
    }

    public static List<IConcoctionContext> getActiveConcotions(EntityLivingBase livingBase) {
        if (LivingConcoctions.get(livingBase) != null && !LivingConcoctions.getActiveConcotions(livingBase).isEmpty()) {
            return LivingConcoctions.getActiveConcotions(livingBase);
        }
        return null;
    }
}
