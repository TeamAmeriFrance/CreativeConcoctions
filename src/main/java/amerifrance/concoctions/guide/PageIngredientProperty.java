package amerifrance.concoctions.guide;

import amerifrance.concoctions.api.CreativeConcoctionsAPI;
import amerifrance.concoctions.api.MapKey;
import amerifrance.concoctions.api.ingredients.IngredientKnowledge;
import amerifrance.concoctions.api.ingredients.IngredientProperties;
import amerifrance.concoctions.api.registry.IngredientsRegistry;
import amerifrance.guideapi.api.abstraction.CategoryAbstract;
import amerifrance.guideapi.api.abstraction.EntryAbstract;
import amerifrance.guideapi.api.base.Book;
import amerifrance.guideapi.api.base.PageBase;
import amerifrance.guideapi.gui.GuiBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.EnumChatFormatting;

public class PageIngredientProperty extends PageBase {

    public IngredientProperties properties;

    public PageIngredientProperty(IngredientProperties properties) {
        this.properties = properties;
    }

    @Override
    public void draw(Book book, CategoryAbstract category, EntryAbstract entry, int guiLeft, int guiTop, int mouseX, int mouseY, GuiBase guiBase, FontRenderer fontRenderer) {
        String text = "Known ingredients: \n ";

        for (MapKey key : IngredientsRegistry.getKeys()) {
            if (IngredientsRegistry.getIngredient(key.stack).getPropertiesList().contains(properties)) {
                if (IngredientKnowledge.getKnowledge(Minecraft.getMinecraft().thePlayer).get(properties))
                    text += key.stack.getDisplayName() + "\n ";
                else
                    text += EnumChatFormatting.BOLD + CreativeConcoctionsAPI.randomString(key.stack.getDisplayName().length()) + EnumChatFormatting.RESET + "\n ";
            }
        }

        fontRenderer.setUnicodeFlag(true);
        fontRenderer.drawSplitString(text, guiLeft + 39, guiTop + 12, 3 * guiBase.xSize / 5, 0);
        fontRenderer.setUnicodeFlag(false);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PageIngredientProperty)) return false;
        if (!super.equals(o)) return false;

        PageIngredientProperty that = (PageIngredientProperty) o;

        if (properties != that.properties) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return properties != null ? properties.hashCode() : 0;
    }
}
