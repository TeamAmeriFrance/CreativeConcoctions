package amerifrance.concoctions.util;

import java.util.Iterator;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import amerifrance.concoctions.api.IConcoctionContext;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ConcoctionsHandler {

    @SubscribeEvent
    public void onCreateEntity(EntityEvent.EntityConstructing event) {
        if (event.entity instanceof EntityLivingBase && LivingConcoctions.get((EntityLivingBase) event.entity) == null) {
            LivingConcoctions.create((EntityLivingBase) event.entity);
        }
    }

    @SubscribeEvent
    public void onLivingTick(LivingEvent.LivingUpdateEvent event) {
        if (event.entityLiving.worldObj.isRemote) return;

        EntityLivingBase livingBase = event.entityLiving;
        if (LivingConcoctions.get(livingBase) != null) {
            if (LivingConcoctions.getActiveConcotions(livingBase) != null && !LivingConcoctions.getActiveConcotions(livingBase).isEmpty()) {
                Iterator<IConcoctionContext> iterator = LivingConcoctions.getActiveConcotions(livingBase).iterator();
                while (iterator.hasNext()) {
                    IConcoctionContext wrapper = iterator.next();
                    if (wrapper.getTicksLeft() > 0) {
                        wrapper.onUpdate(livingBase);
                    } else {
                        wrapper.onRemoved(livingBase);
                        iterator.remove();
                    }
                }
            }
        }
    }
}
