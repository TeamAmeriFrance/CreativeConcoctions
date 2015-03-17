package amerifrance.concoctions.util;

import amerifrance.concoctions.api.IConcoctionContext;
import amerifrance.concoctions.util.LivingConcoctions;
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
        if (event.entityLiving.worldObj.isRemote) return;

        EntityLivingBase entityLivingBase = event.entityLiving;
        if (LivingConcoctions.get(entityLivingBase) != null) {
            if (LivingConcoctions.getActiveConcotions(entityLivingBase) != null && !LivingConcoctions.getActiveConcotions(entityLivingBase).isEmpty()) {
                Iterator<IConcoctionContext> iterator = LivingConcoctions.getActiveConcotions(entityLivingBase).iterator();
                while (iterator.hasNext()) {
                    IConcoctionContext wrapper = iterator.next();
                    if (wrapper.getTicksLeft() > 0) {
                        wrapper.onUpdate(entityLivingBase);
                    } else {
                        wrapper.onRemoved(entityLivingBase);
                        iterator.remove();
                    }
                }
            }
        }
    }
}
