package amerifrance.concoctions.util;

import amerifrance.concoctions.api.ConcoctionsHelper;
import amerifrance.concoctions.api.IConcoctionContext;
import amerifrance.concoctions.concoctions.ModConcoctions;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class EventHandler {

    @SubscribeEvent
    public void addConcoction(LivingEvent.LivingJumpEvent event) {
        if (event.entityLiving instanceof EntityPlayer && !((EntityPlayer) event.entityLiving).worldObj.isRemote) {
            if (ConcoctionsHelper.isConcoctionActive(event.entityLiving, ModConcoctions.jumpBoost)) {
                IConcoctionContext ctx = ConcoctionsHelper.getActiveConcoction(event.entityLiving, ModConcoctions.jumpBoost);
                if (ctx != null && ctx.getConcoctionLevel() + 1 <= ctx.getConcoction().maxLevel) {
                    ctx.setLevel(ctx.getConcoctionLevel() + 1);
                    ctx.onAdded(event.entityLiving);
                }
            } else {
                ConcoctionsHelper.addConcoction(event.entityLiving, ModConcoctions.jumpBoost, 1, 500);
            }
        }
    }

    @SubscribeEvent
    public void onDealFireDamage(LivingHurtEvent event) {
        if (event.source.isFireDamage() && ConcoctionsHelper.isConcoctionActive(event.entityLiving, ModConcoctions.fireProtection)) {
            IConcoctionContext ctx = ConcoctionsHelper.getActiveConcoction(event.entityLiving, ModConcoctions.fireProtection);
            if (event.entityLiving.isInsideOfMaterial(Material.lava)) {
                if (ctx.getConcoctionLevel() == 2) {
                    event.ammount = event.ammount / 2;
                } else if (ctx.getConcoctionLevel() >= 3) {
                    event.setCanceled(true);
                }
            } else {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public void onJump(LivingEvent.LivingJumpEvent event) {
        if (!ConcoctionsHelper.isConcoctionActive(event.entityLiving, ModConcoctions.jumpBoost)) return;
        IConcoctionContext ctx = ConcoctionsHelper.getActiveConcoction(event.entityLiving, ModConcoctions.jumpBoost);
        event.entityLiving.motionY += 0.1F * ctx.getConcoctionLevel();
    }
}
