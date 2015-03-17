package amerifrance.concoctions.util;

import amerifrance.concoctions.api.ConcoctionsHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;

public class EventHandler {

    @SubscribeEvent
    public void onJump(LivingEvent.LivingJumpEvent event) {
        if (event.entityLiving instanceof EntityPlayer && !((EntityPlayer) event.entityLiving).worldObj.isRemote) {
            ConcoctionsHelper.addConcoction(event.entityLiving, "Concoction.Test", 1, 200);
        }
    }
}
