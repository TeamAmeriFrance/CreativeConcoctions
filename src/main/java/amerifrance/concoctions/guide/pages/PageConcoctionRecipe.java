package amerifrance.concoctions.guide.pages;

import amerifrance.concoctions.api.concoctions.Concoction;
import amerifrance.concoctions.api.ingredients.IngredientProperty;
import amerifrance.concoctions.api.registry.ConcoctionRecipes;
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

import java.util.List;

public class PageConcoctionRecipe extends PageBase {

    public Concoction concoction;

    /**
     *
     * @param concoction - {@link Concoction} to grab the ingredient list of.
     */
    public PageConcoctionRecipe(Concoction concoction) {
        this.concoction = concoction;
    }

    @SideOnly(Side.CLIENT)
    public void draw(Book book, CategoryAbstract category, EntryAbstract entry, int guiLeft, int guiTop, int mouseX, int mouseY, GuiBase guiBase, FontRenderer fontRenderer) {

        fontRenderer.drawString(EnumChatFormatting.BLACK + StatCollector.translateToLocal("guide.creativeconcoctions.page.recipe.title"), guiLeft + 59, guiTop + 12, 3 * guiBase.xSize / 5);

        List<IngredientProperty> ingredients = ConcoctionRecipes.getIngredientsForConcoction(concoction);

        if (ingredients == null) {
            fontRenderer.setUnicodeFlag(true);
            fontRenderer.drawString(EnumChatFormatting.BLACK + StatCollector.translateToLocal("guide.creativeconcoctions.page.recipe.wrong"), guiLeft + 39, guiTop + 22, 3 * guiBase.xSize / 5);
            fontRenderer.setUnicodeFlag(false);
            return;
        }

        int drawY = guiTop + 22;
        for (int i = 0; i < ingredients.size(); i++) {
            fontRenderer.setUnicodeFlag(true);
            fontRenderer.drawString(EnumChatFormatting.BLACK + (i + 1 + ". ") + ingredients.get(i).toString(), guiLeft + 39, drawY, 3 * guiBase.xSize / 5);
            fontRenderer.setUnicodeFlag(false);

            drawY = drawY + 10;
        }
    }
}
