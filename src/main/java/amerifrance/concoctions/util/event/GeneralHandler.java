package amerifrance.concoctions.util.event;

import amerifrance.concoctions.ConfigHandler;
import amerifrance.concoctions.CreativeConcoctions;
import amerifrance.concoctions.ModInformation;
import amerifrance.concoctions.api.ingredients.Ingredient;
import amerifrance.concoctions.api.ingredients.IngredientProperty;
import amerifrance.concoctions.api.registry.IngredientsRegistry;
import amerifrance.concoctions.util.LogHelper;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class GeneralHandler {

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
        if (eventArgs.modID.equals(ModInformation.ID)) {
            ConfigHandler.syncConfig();
            LogHelper.info("Refreshing configuration file.");
        }
    }

    public static class GuiHandler {

        @SubscribeEvent(priority = EventPriority.HIGH)
        //TEMPORARY
        public void onIngredientTooltip(ItemTooltipEvent event) {
            Ingredient ingredient = IngredientsRegistry.getIngredient(event.itemStack);
            if (ingredient != null && GuiScreen.isShiftKeyDown()) {
                for (IngredientProperty ingredientProperty : ingredient.getProperties()) {
                    event.toolTip.add(ingredientProperty.name());
                }
                event.toolTip.add(String.valueOf(ingredient.potency));
                event.toolTip.add(String.valueOf(ingredient.instability));
            }
        }

        @SubscribeEvent
        @SuppressWarnings("unchecked")
        @SideOnly(Side.CLIENT)
        public void onGuiPostInit(GuiScreenEvent.InitGuiEvent.Post event) {
            if (event.gui instanceof GuiInventory) {
                int xSize = 176;
                int ySize = 166;
                int guiLeft = (event.gui.width + 31) / 2;
                int guiTop = (event.gui.height - ySize) / 2;
                event.buttonList.add(new GuiButton(191, guiLeft, guiTop - 13, 70, 13, StatCollector.translateToLocal("gui.text.concoctions")));
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
}
