package amerifrance.concoctions.guide.property;

import amerifrance.concoctions.api.CreativeConcoctionsAPI;
import amerifrance.concoctions.api.ingredients.IngredientKnowledge;
import amerifrance.concoctions.api.ingredients.IngredientProperty;
import amerifrance.guideapi.api.util.GuiHelper;
import amerifrance.guideapi.gui.GuiBase;
import amerifrance.guideapi.wrappers.AbstractWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class PropertyWrapper extends AbstractWrapper {

    public int wrapperX, wrapperY;
    public int width, height;
    public EntityPlayer player;
    public FontRenderer renderer;
    public ItemStack stack;
    public String text;
    public IngredientProperty ingredientProperty;
    public boolean hasKnowledge;

    public PropertyWrapper(int wrapperX, int wrapperY, int width, int height, EntityPlayer player, FontRenderer renderer, ItemStack stack, IngredientProperty ingredientProperty) {
        this.wrapperX = wrapperX;
        this.wrapperY = wrapperY;
        this.width = width;
        this.height = height;
        this.player = player;
        this.renderer = renderer;
        this.stack = stack;
        this.text = " " + stack.getDisplayName();
        this.ingredientProperty = ingredientProperty;
        this.hasKnowledge = IngredientKnowledge.getKnowledge(Minecraft.getMinecraft().thePlayer).get(ingredientProperty);
    }

    @Override
    public void onHoverOver(int mouseX, int mouseY) {
        if (hasKnowledge) GuiHelper.drawItemStack(stack, mouseX, mouseY);
    }

    @Override
    public boolean canPlayerSee() {
        return true;
    }

    @Override
    public void draw(int mouseX, int mouseY, GuiBase gui) {
        renderer.setUnicodeFlag(true);
        if (!hasKnowledge) {
            renderer.drawString(EnumChatFormatting.BOLD + CreativeConcoctionsAPI.randomString(text.length()) + EnumChatFormatting.RESET, wrapperX, wrapperY, 0);
        } else {
            renderer.drawString(text, wrapperX, wrapperY, 0);
        }
        renderer.setUnicodeFlag(false);
    }

    @Override
    public void drawExtras(int mouseX, int mouseY, GuiBase gui) {
    }

    @Override
    public boolean isMouseOnWrapper(int mouseX, int mouseY) {
        return GuiHelper.isMouseBetween(mouseX, mouseY, wrapperX, wrapperY, width, height);
    }
}
