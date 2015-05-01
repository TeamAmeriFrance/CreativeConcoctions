package amerifrance.concoctions.util.event;

import amerifrance.concoctions.api.CreativeConcoctionsAPI;
import amerifrance.concoctions.api.concoctions.ConcoctionsHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumChatFormatting;
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

    @SubscribeEvent
    public void onCoatedTooltip(ItemTooltipEvent event) {
        if (event.itemStack != null && event.itemStack.getItem() instanceof ItemSword) {
            ItemStack stack = event.itemStack;

            if (CreativeConcoctionsAPI.getConcoction(stack) != null) {
                event.toolTip.set(0, stack.getDisplayName() + " (" + CreativeConcoctionsAPI.getConcoction(stack).getConcotionType().prefix + CreativeConcoctionsAPI.getConcoction(stack).name + EnumChatFormatting.RESET + ")");

                if (GuiScreen.isShiftKeyDown()) {
                    event.toolTip.add(1, CreativeConcoctionsAPI.getConcoction(stack).getConcotionType().prefix + CreativeConcoctionsAPI.getConcoction(stack).name);
                    event.toolTip.add(2, String.format(StatCollector.translateToLocal("gui.text.level"), CreativeConcoctionsAPI.getLevel(stack)));
                    if (!CreativeConcoctionsAPI.getConcoction(stack).isConcoctionInstant())
                        event.toolTip.add(3, String.format(StatCollector.translateToLocal("gui.text.duration"), (double) CreativeConcoctionsAPI.getDuration(stack) / 20 + "s"));
                }
            }
        }
    }
}
