package amerifrance.concoctions.api;

import amerifrance.concoctions.ConcoctionContext;
import amerifrance.concoctions.util.LivingConcoctions;
import net.minecraft.entity.EntityLivingBase;

import java.util.Iterator;
import java.util.List;

public class ConcoctionsHelper {

    public static void addConcoction(EntityLivingBase livingBase, String id, int level, int duration) {
        IConcoctionContext ctx = new ConcoctionContext(ConcoctionsRegistry.getConcoctionForId(id), level, duration);
        addConcoction(livingBase, ctx);
    }

    public static void addConcoction(EntityLivingBase livingBase, Concoction concoction, int level, int duration) {
        IConcoctionContext ctx = new ConcoctionContext(concoction, level, duration);
        addConcoction(livingBase, ctx);
    }

    public static void addConcoction(EntityLivingBase livingBase, IConcoctionContext ctx) {
        if (LivingConcoctions.get(livingBase) != null) {
            if (ctx.getConcoctionLevel() > ctx.getConcoction().maxLevel) {
                ctx.setLevel(ctx.getConcoction().maxLevel);
            }
            LivingConcoctions.getActiveConcotions(livingBase).add(ctx);
            if (!livingBase.worldObj.isRemote) ctx.onAdded(livingBase);
        }
    }

    public static void removeConcoction(EntityLivingBase livingBase, String id) {
        removeConcoction(livingBase, getActiveConcoction(livingBase, ConcoctionsRegistry.getConcoctionForId(id)));
    }

    public static void removeConcoction(EntityLivingBase livingBase, Concoction concoction) {
        removeConcoction(livingBase, getActiveConcoction(livingBase, concoction));
    }

    public static void removeConcoction(EntityLivingBase livingBase, IConcoctionContext ctx) {
        if (ctx != null) {
            if (LivingConcoctions.get(livingBase) != null && !LivingConcoctions.getActiveConcotions(livingBase).isEmpty()) {
                if (LivingConcoctions.getActiveConcotions(livingBase).contains(ctx)) {
                    int index = LivingConcoctions.getActiveConcotions(livingBase).indexOf(ctx);
                    IConcoctionContext remove = LivingConcoctions.getActiveConcotions(livingBase).get(index);
                    remove.setTicksLeft(0);
                }
            }
        }
    }

    public static IConcoctionContext getActiveConcoction(EntityLivingBase livingBase, String id) {
        return getActiveConcoction(livingBase, ConcoctionsRegistry.getConcoctionForId(id));
    }

    public static IConcoctionContext getActiveConcoction(EntityLivingBase livingBase, Concoction concoction) {
        if (LivingConcoctions.get(livingBase) != null && !LivingConcoctions.getActiveConcotions(livingBase).isEmpty()) {
            Iterator<IConcoctionContext> iterator = LivingConcoctions.getActiveConcotions(livingBase).iterator();
            while (iterator.hasNext()) {
                IConcoctionContext ctx = iterator.next();
                if (ctx.getConcoction().equals(concoction)) return ctx;
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
        LivingConcoctions data = LivingConcoctions.get(livingBase);
        if (data != null) {
            data.activeConcoctions.clear();
        }
    }

    public static List<IConcoctionContext> getActiveConcotions(EntityLivingBase livingBase) {
        if (LivingConcoctions.get(livingBase) != null && !LivingConcoctions.getActiveConcotions(livingBase).isEmpty()) {
            return LivingConcoctions.getActiveConcotions(livingBase);
        }
        return null;
    }
}
