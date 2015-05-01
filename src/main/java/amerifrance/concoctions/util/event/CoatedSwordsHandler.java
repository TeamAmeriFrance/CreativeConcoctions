package amerifrance.concoctions.util.event;

import amerifrance.concoctions.api.CreativeConcoctionsAPI;
import amerifrance.concoctions.api.concoctions.ConcoctionsHelper;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class CoatedSwordsHandler {

    @SubscribeEvent
    public void onLivingHit(LivingHurtEvent event) {
        if (event.source.getEntity() != null && event.source.getEntity() instanceof EntityLivingBase) {
            EntityLivingBase attacker = (EntityLivingBase) event.source.getEntity();
            EntityLivingBase target = event.entityLiving;

            if (attacker.getHeldItem() != null && attacker.getHeldItem().getItem() instanceof ItemSword) {
                ItemStack stack = attacker.getHeldItem();
                if (CreativeConcoctionsAPI.getConcoction(stack) != null) {
                    ConcoctionsHelper.addConcoction(target, CreativeConcoctionsAPI.getConcoction(stack), CreativeConcoctionsAPI.getLevel(stack), CreativeConcoctionsAPI.getDuration(stack));
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onCoatedTooltip(ItemTooltipEvent event) {
        if (event.itemStack != null && event.itemStack.getItem() instanceof ItemSword) {
            if (!GuiScreen.isShiftKeyDown()) return;
            ItemStack stack = event.itemStack;

            if (CreativeConcoctionsAPI.getConcoction(stack) != null) {
                event.toolTip.add(CreativeConcoctionsAPI.getConcoction(stack).getConcotionType().prefix + CreativeConcoctionsAPI.getConcoction(stack).name);
                event.toolTip.add(String.format(StatCollector.translateToLocal("gui.text.level"), CreativeConcoctionsAPI.getLevel(stack)));
                if (!CreativeConcoctionsAPI.getConcoction(stack).isConcoctionInstant())
                    event.toolTip.add(String.format(StatCollector.translateToLocal("gui.text.duration"), (double) CreativeConcoctionsAPI.getDuration(stack) / 20 + "s"));
            }
        }
    }
}
