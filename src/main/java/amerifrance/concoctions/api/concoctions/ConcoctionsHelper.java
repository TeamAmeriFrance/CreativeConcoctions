package amerifrance.concoctions.api.concoctions;

import amerifrance.concoctions.api.registry.ConcoctionsRegistry;
import amerifrance.concoctions.util.ConcoctionContext;
import net.minecraft.entity.EntityLivingBase;

import java.util.Iterator;
import java.util.LinkedList;
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
        if (LivingConcoctions.get(livingBase) != null && ctx != null) {
            if (ctx.getConcoctionLevel() > ctx.getConcoction().maxLevel)
                ctx.setLevel(ctx.getConcoction().maxLevel);

            if (ctx.getConcoction().isConcoctionInstant())
                ctx.setDuration(20);

            if (getActiveConcoction(livingBase, ctx.getConcoction()) != null) {
                IConcoctionContext concoctionContext = getActiveConcoction(livingBase, ctx.getConcoction());
                ctx.setLevel(Math.max(ctx.getConcoctionLevel(), concoctionContext.getConcoctionLevel()));
                ctx.setDuration(ctx.getInitialDuration() + concoctionContext.getTicksLeft());
                LivingConcoctions.getActiveConcotions(livingBase).remove(concoctionContext);
            }

            LivingConcoctions.getActiveConcotions(livingBase).add(ctx);
            ctx.onAdded(livingBase);
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
                    if (!livingBase.worldObj.isRemote) ctx.onRemoved(livingBase);
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
        LinkedList<IConcoctionContext> toRemove = new LinkedList<IConcoctionContext>();
        Iterator<IConcoctionContext> iterator = LivingConcoctions.getActiveConcotions(livingBase).iterator();
        while (iterator.hasNext()) {
            IConcoctionContext ctx = iterator.next();
            ctx.setTicksLeft(0);
            toRemove.add(ctx);
        }
        for (IConcoctionContext ctx : toRemove) ctx.onRemoved(livingBase);
    }

    public static List<IConcoctionContext> getActiveConcotions(EntityLivingBase livingBase) {
        if (LivingConcoctions.get(livingBase) != null && !LivingConcoctions.getActiveConcotions(livingBase).isEmpty()) {
            return LivingConcoctions.getActiveConcotions(livingBase);
        }
        return null;
    }
}
