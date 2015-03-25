package amerifrance.concoctions.util;

import amerifrance.concoctions.api.concoctions.IConcoctionContext;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

import java.util.Iterator;

public class ConcoctionsHandler {

    @SubscribeEvent
    public void onCreateEntity(EntityEvent.EntityConstructing event) {
        if (event.entity instanceof EntityLivingBase && LivingConcoctions.get((EntityLivingBase) event.entity) == null) {
            LivingConcoctions.create((EntityLivingBase) event.entity);
        }
    }

    @SubscribeEvent
    public void onLivingTick(LivingEvent.LivingUpdateEvent event) {
        EntityLivingBase livingBase = event.entityLiving;
        if (LivingConcoctions.get(livingBase) != null) {
            if (LivingConcoctions.getActiveConcotions(livingBase) != null && !LivingConcoctions.getActiveConcotions(livingBase).isEmpty()) {
                Iterator<IConcoctionContext> iterator = LivingConcoctions.getActiveConcotions(livingBase).iterator();
                while (iterator.hasNext()) {
                    IConcoctionContext ctx = iterator.next();
                    if (ctx.getTicksLeft() > 0) {
                        ctx.onUpdate(livingBase);
                    } else {
                        if (!event.entityLiving.worldObj.isRemote) ctx.onRemoved(livingBase);
                        iterator.remove();
                    }
                }
            }
        }
    }
}
