package amerifrance.concoctions.gui;

import amerifrance.concoctions.api.concoctions.IConcoctionContext;
import amerifrance.concoctions.network.PacketHandler;
import amerifrance.concoctions.network.PacketOpenInventory;
import amerifrance.concoctions.api.concoctions.LivingConcoctions;
import amerifrance.guideapi.ModInformation;
import amerifrance.guideapi.buttons.ButtonBack;
import amerifrance.guideapi.buttons.ButtonNext;
import amerifrance.guideapi.buttons.ButtonPrev;
import amerifrance.guideapi.gui.GuiBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class GuiConcoctions extends GuiBase {

    public ResourceLocation outlineTexture = new ResourceLocation(ModInformation.GUITEXLOC + "book_greyscale.png");
    public ResourceLocation pageTexture = new ResourceLocation(ModInformation.GUITEXLOC + "book_colored.png");

    public ButtonBack buttonBack;
    public ButtonNext buttonNext;
    public ButtonPrev buttonPrev;
    private int pageNumber;
    private int maxPage;

    public GuiConcoctions(EntityPlayer player) {
        super(player, null);
        this.pageNumber = 0;
        this.maxPage = 0;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.clear();

        guiLeft = (this.width - this.xSize) / 2;
        guiTop = (this.height - this.ySize) / 2;

        this.buttonList.add(buttonBack = new ButtonBack(0, guiLeft + xSize / 6, guiTop, this));
        this.buttonList.add(buttonNext = new ButtonNext(1, guiLeft + 4 * xSize / 6, guiTop + 5 * ySize / 6, this));
        this.buttonList.add(buttonPrev = new ButtonPrev(2, guiLeft + xSize / 5, guiTop + 5 * ySize / 6, this));

        if (LivingConcoctions.getActiveConcotions(player) != null && !LivingConcoctions.getActiveConcotions(player).isEmpty()) {
            this.maxPage = LivingConcoctions.getActiveConcotions(player).size();
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float renderPartialTicks) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(pageTexture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        Minecraft.getMinecraft().getTextureManager().bindTexture(outlineTexture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        if (LivingConcoctions.getActiveConcotions(player) != null && !LivingConcoctions.getActiveConcotions(player).isEmpty()) {
            List<IConcoctionContext> list = LivingConcoctions.getActiveConcotions(player);
            maxPage = list.size();
            if (pageNumber < maxPage) {
                IConcoctionContext ctx = list.get(pageNumber);
                String s = StatCollector.translateToLocal("gui.text.concoction") + ": ";
                s += ctx.getConcoction().name + "\n";
                s += StatCollector.translateToLocal("gui.text.level") + ": " + String.valueOf(ctx.getConcoctionLevel()) + "\n";
                s += StatCollector.translateToLocal("gui.text.time.left") + ": " + String.valueOf(ctx.getTicksLeft() / 20) + " s" + "\n";

                drawSplitString(s, guiLeft + 37, guiTop + 12, (4 * xSize / 6) - 4, 0);
            } else {
                pageNumber = maxPage - 1;
            }
            drawCenteredString(fontRendererObj, String.valueOf(pageNumber + 1) + "/" + String.valueOf(maxPage), guiLeft + xSize / 2, guiTop + 5 * ySize / 6, 0);
        }
        super.drawScreen(mouseX, mouseY, renderPartialTicks);
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
        super.keyTyped(typedChar, keyCode);
        if (keyCode == Keyboard.KEY_BACK || keyCode == this.mc.gameSettings.keyBindUseItem.getKeyCode()) {
            mc.displayGuiScreen(new GuiInventory(mc.thePlayer));
            PacketHandler.INSTANCE.sendToServer(new PacketOpenInventory(mc.thePlayer));
        }
        if ((keyCode == Keyboard.KEY_UP || keyCode == Keyboard.KEY_RIGHT) && pageNumber + 1 < maxPage) {
            this.pageNumber++;
        }
        if ((keyCode == Keyboard.KEY_DOWN || keyCode == Keyboard.KEY_LEFT) && pageNumber > 0) {
            this.pageNumber--;
        }
    }

    @Override
    public void actionPerformed(GuiButton button) {
        if (button.id == 0) {
            mc.displayGuiScreen(new GuiInventory(mc.thePlayer));
            PacketHandler.INSTANCE.sendToServer(new PacketOpenInventory(mc.thePlayer));
        } else if (button.id == 1 && pageNumber + 1 < maxPage) {
            this.pageNumber++;
            initGui();
        } else if (button.id == 2 && pageNumber > 0) {
            this.pageNumber--;
            initGui();
        }
    }
}
