package amerifrance.concoctions.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import amerifrance.concoctions.api.ConcoctionsHelper;
import amerifrance.concoctions.api.IConcoctionContext;
import amerifrance.concoctions.concoctions.ModConcoctions;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EventHandler {

    @SubscribeEvent
    public void onJump(LivingEvent.LivingJumpEvent event) {
        if (event.entityLiving instanceof EntityPlayer && !((EntityPlayer) event.entityLiving).worldObj.isRemote) {
            if (ConcoctionsHelper.isConcoctionActive(event.entityLiving, ModConcoctions.slowness)) {
                IConcoctionContext ctx = ConcoctionsHelper.getActiveConcoction(event.entityLiving, ModConcoctions.slowness);
                if (ctx != null && ctx.getConcoctionLevel() + 1 <= ctx.getConcoction().maxLevel) {
                    ctx.setLevel(ctx.getConcoctionLevel() + 1);
                    ctx.onAdded(event.entityLiving);
                }
            } else {
                ConcoctionsHelper.addConcoction(event.entityLiving, ModConcoctions.slowness, 1, 500);
            }
        }
    }
}
