package amerifrance.concoctions.guide.pages;

import amerifrance.concoctions.api.concoctions.Concoction;
import amerifrance.guideapi.api.abstraction.CategoryAbstract;
import amerifrance.guideapi.api.abstraction.EntryAbstract;
import amerifrance.guideapi.api.base.Book;
import amerifrance.guideapi.api.base.PageBase;
import amerifrance.guideapi.gui.GuiBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public class PageConcoctionList extends PageBase {

    public String unlocPageTitle;
    public Concoction[] concoctions;

    /**
     * @param unlocPageTitle - Unlocalized title of the page
     * @param concoctions    - List of Concoctions to draw
     */
    public PageConcoctionList(String unlocPageTitle, Concoction[] concoctions) {
        this.unlocPageTitle = unlocPageTitle;
        this.concoctions = concoctions;
    }

    @SideOnly(Side.CLIENT)
    public void draw(Book book, CategoryAbstract category, EntryAbstract entry, int guiLeft, int guiTop, int mouseX, int mouseY, GuiBase guiBase, FontRenderer fontRenderer) {

        fontRenderer.drawString(EnumChatFormatting.BLACK + StatCollector.translateToLocal(unlocPageTitle), guiLeft + 59, guiTop + 12, 3 * guiBase.xSize / 5);

        int drawY = guiTop + 22;
        for (int i = 0; i < concoctions.length; i++) {

            fontRenderer.setUnicodeFlag(true);
            fontRenderer.drawString(EnumChatFormatting.BLACK + (i + 1 + ". ") + concoctions[i].name, guiLeft + 39, drawY, 3 * guiBase.xSize / 5);
            fontRenderer.setUnicodeFlag(false);

            drawY = drawY + 10;
        }
    }
}
