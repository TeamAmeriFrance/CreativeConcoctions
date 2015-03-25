package amerifrance.concoctions.util;

import amerifrance.concoctions.api.concoctions.ConcoctionsHelper;
import amerifrance.concoctions.api.concoctions.IConcoctionContext;
import amerifrance.concoctions.concoctions.ModConcoctions;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.material.Material;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class EventHandler {

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
        IConcoctionContext jumpBoost = ConcoctionsHelper.getActiveConcoction(event.entityLiving, ModConcoctions.jumpBoost);
        if (jumpBoost != null) event.entityLiving.motionY += 0.1F * jumpBoost.getConcoctionLevel();
    }

    @SubscribeEvent
    public void onFall(LivingFallEvent event) {
        IConcoctionContext featherFall = ConcoctionsHelper.getActiveConcoction(event.entityLiving, ModConcoctions.featherFall);
        if (featherFall != null) event.distance -= 1.0F * featherFall.getConcoctionLevel();
    }

    @SubscribeEvent
    public void onHurt(LivingHurtEvent event) {
        IConcoctionContext resistance = ConcoctionsHelper.getActiveConcoction(event.entityLiving, ModConcoctions.resistance);
        if (resistance != null && !event.source.isFireDamage() && event.source != DamageSource.fall)
            event.ammount -= 0.5F * resistance.getConcoctionLevel();
    }

    @SubscribeEvent
    public void onBreakingBlock(PlayerEvent.BreakSpeed event) {
        IConcoctionContext slow = ConcoctionsHelper.getActiveConcoction(event.entityLiving, ModConcoctions.mineSlow);
        if (slow != null) event.newSpeed = event.originalSpeed - 0.1F * slow.getConcoctionLevel();

        IConcoctionContext fast = ConcoctionsHelper.getActiveConcoction(event.entityLiving, ModConcoctions.mineFast);
        if (fast != null) event.newSpeed = event.originalSpeed + 0.1F * fast.getConcoctionLevel();
    }
}
