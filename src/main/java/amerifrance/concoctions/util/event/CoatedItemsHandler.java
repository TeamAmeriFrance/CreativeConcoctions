package amerifrance.concoctions.util.event;

import amerifrance.concoctions.api.CreativeConcoctionsAPI;
import amerifrance.concoctions.api.concoctions.ConcoctionsHelper;
import amerifrance.concoctions.api.registry.CoatedItemsRegistry;
import amerifrance.concoctions.api.util.NBTTags;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class CoatedItemsHandler {

    @SubscribeEvent
    public void onLivingHit(LivingHurtEvent event) {
        if (event.source.getEntity() != null && event.source.getEntity() instanceof EntityLivingBase) {
            EntityLivingBase attacker = (EntityLivingBase) event.source.getEntity();
            EntityLivingBase target = event.entityLiving;

            if (attacker.getHeldItem() != null && CoatedItemsRegistry.hasKey(attacker.getHeldItem().getItem())) {
                ItemStack stack = attacker.getHeldItem();
                if (CreativeConcoctionsAPI.getConcoction(stack) != null && CreativeConcoctionsAPI.getUsesLeft(stack) > 0) {
                    ConcoctionsHelper.addConcoction(target, CreativeConcoctionsAPI.getConcoction(stack), CreativeConcoctionsAPI.getLevel(stack), CreativeConcoctionsAPI.getDuration(stack));
                    CreativeConcoctionsAPI.setUsesLeft(stack, CreativeConcoctionsAPI.getUsesLeft(stack) - 1);
                } else if (stack.stackTagCompound.hasKey(NBTTags.ID_TAG)) {
                    stack.stackTagCompound.removeTag(NBTTags.ID_TAG);
                }
            }
        }
    }

    @SubscribeEvent
    public void onCoatedTooltip(ItemTooltipEvent event) {
        if (event.itemStack != null && event.itemStack.getItem() instanceof ItemSword) {
            ItemStack stack = event.itemStack;

            if (CoatedItemsRegistry.hasKey(event.itemStack.getItem()) && CreativeConcoctionsAPI.getConcoction(stack) != null) {
                event.toolTip.set(0, stack.getDisplayName() + " (" + CreativeConcoctionsAPI.getConcoction(stack).getConcotionType().prefix + CreativeConcoctionsAPI.getConcoction(stack).name + EnumChatFormatting.RESET + ")");

                if (GuiScreen.isShiftKeyDown()) {
                    event.toolTip.add(1, CreativeConcoctionsAPI.getConcoction(stack).getConcotionType().prefix + CreativeConcoctionsAPI.getConcoction(stack).name);
                    event.toolTip.add(2, String.format(StatCollector.translateToLocal("gui.text.level"), CreativeConcoctionsAPI.getLevel(stack)));
                    if (!CreativeConcoctionsAPI.getConcoction(stack).isConcoctionInstant())
                        event.toolTip.add(3, String.format(StatCollector.translateToLocal("gui.text.duration"), (double) CreativeConcoctionsAPI.getDuration(stack) / 20 + "s"));
                    event.toolTip.add(4, String.format(StatCollector.translateToLocal("gui.text.uses.left"), CreativeConcoctionsAPI.getUsesLeft(stack)));
                }
            }
        }
    }
}
