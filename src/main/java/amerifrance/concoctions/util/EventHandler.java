package amerifrance.concoctions.util;

import amerifrance.concoctions.api.ConcoctionsHelper;
import amerifrance.concoctions.concoctions.ModConcoctions;
import amerifrance.concoctions.objects.ConcoctionContext;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;

public class EventHandler {

    @SubscribeEvent
    public void onJump(LivingEvent.LivingJumpEvent event) {
        if (event.entityLiving instanceof EntityPlayer && !((EntityPlayer) event.entityLiving).worldObj.isRemote) {
            if (ConcoctionsHelper.isConcoctionActive(event.entityLiving, ModConcoctions.speed)) {
                ConcoctionContext ctx = ConcoctionsHelper.getActiveConcoction(event.entityLiving, ModConcoctions.speed);
                if (ctx != null && ctx.getConcoctionLevel() + 1 <= ctx.getConcoction().maxLevel) {
                    ctx.setLevel(ctx.getConcoctionLevel() + 1);
                    ctx.onAdded(event.entityLiving);
                }
            } else {
                ConcoctionsHelper.addConcoction(event.entityLiving, ModConcoctions.speed, 1, 500);
            }
        }
    }
}
