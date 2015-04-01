package amerifrance.concoctions.util;

import amerifrance.concoctions.CreativeConcoctions;
import amerifrance.concoctions.api.concoctions.ConcoctionsHelper;
import amerifrance.concoctions.api.concoctions.IConcoctionContext;
import amerifrance.concoctions.api.ingredients.Ingredient;
import amerifrance.concoctions.api.ingredients.IngredientProperties;
import amerifrance.concoctions.api.registry.IngredientsRegistry;
import amerifrance.concoctions.concoctions.ModConcoctions;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
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

    @SubscribeEvent(priority = EventPriority.HIGH)
    //TEMPORARY
    public void onIngredientTooltip(ItemTooltipEvent event) {
        Ingredient ingredient = IngredientsRegistry.getIngredient(event.itemStack);
        if (ingredient != null && GuiScreen.isShiftKeyDown()) {
            for (IngredientProperties properties : ingredient.getProperties()) {
                event.toolTip.add(properties.name());
            }
            event.toolTip.add(String.valueOf(ingredient.potency));
            event.toolTip.add(String.valueOf(ingredient.stability));
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onGuiPostInit(GuiScreenEvent.InitGuiEvent.Post event) {
        if (event.gui instanceof GuiInventory) {
            int xSize = 176;
            int ySize = 166;
            int guiLeft = (event.gui.width - xSize) / 2;
            int guiTop = (event.gui.height - ySize) / 2;
            event.buttonList.add(new GuiButton(191, 3 * xSize / 2, guiTop - 15, 50, 15, "Concoctions"));
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onButtonClicked(GuiScreenEvent.ActionPerformedEvent.Post event) {
        if (event.gui instanceof GuiInventory) {
            if (event.button.id == 191) {
                EntityPlayer player = event.gui.mc.thePlayer;
                player.openGui(CreativeConcoctions.instance, 0, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
            }
        }
    }
}
