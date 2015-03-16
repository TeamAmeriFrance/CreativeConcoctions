package amerifrance.concoctions;

import amerifrance.concoctions.objects.ConcoctionWrapper;
import amerifrance.concoctions.util.PlayerConcoctions;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

import java.util.List;

public class ConcoctionsHandler {

    @SubscribeEvent
    public void onCreateEntity(EntityEvent.EntityConstructing event) {
        if (event.entity instanceof EntityLivingBase && PlayerConcoctions.get((EntityLivingBase) event.entity) == null) {
            PlayerConcoctions.create((EntityLivingBase) event.entity);
        }
    }

    @SubscribeEvent
    public void onLivingTick(LivingEvent.LivingUpdateEvent event) {
        EntityLivingBase entityLivingBase = event.entityLiving;
        List<ConcoctionWrapper> activeConcoctions = PlayerConcoctions.getActiveConcotions(entityLivingBase);
        if (activeConcoctions != null && !activeConcoctions.isEmpty()) {
            for (ConcoctionWrapper wrapper : activeConcoctions) wrapper.onUpdate(entityLivingBase);
        }
    }
}
