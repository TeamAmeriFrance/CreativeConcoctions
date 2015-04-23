package amerifrance.concoctions.guide.property;

import amerifrance.concoctions.api.CreativeConcoctionsAPI;
import amerifrance.concoctions.api.ingredients.Ingredient;
import amerifrance.concoctions.api.ingredients.IngredientKnowledge;
import amerifrance.concoctions.api.ingredients.IngredientProperty;
import amerifrance.concoctions.api.registry.IngredientsRegistry;
import amerifrance.guideapi.api.util.GuiHelper;
import amerifrance.guideapi.gui.GuiBase;
import amerifrance.guideapi.wrappers.AbstractWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import java.util.ArrayList;
import java.util.List;

public class PropertyWrapper extends AbstractWrapper {

    public int wrapperX, wrapperY;
    public int width, height;
    public EntityPlayer player;
    public FontRenderer renderer;
    public ItemStack stack;
    public IngredientProperty ingredientProperty;

    private String text;
    private boolean hasKnowledge;
    private Ingredient ingredient;
    private int potency;
    private float instability;
    private double timeToBoil;
    private String ingredientType;
    private List<String> tooltip;

    public PropertyWrapper(int wrapperX, int wrapperY, int width, int height, EntityPlayer player, FontRenderer renderer, ItemStack stack, IngredientProperty ingredientProperty) {
        this.wrapperX = wrapperX;
        this.wrapperY = wrapperY;
        this.width = width;
        this.height = height;
        this.player = player;
        this.renderer = renderer;
        this.stack = stack;
        this.ingredientProperty = ingredientProperty;

        this.text = " " + stack.getDisplayName();
        this.hasKnowledge = IngredientKnowledge.getKnowledge(Minecraft.getMinecraft().thePlayer).get(ingredientProperty);
        this.ingredient = IngredientsRegistry.getIngredient(stack);
        this.potency = this.ingredient.potency;
        this.instability = this.ingredient.instability;
        this.timeToBoil = (double) this.ingredient.ticksToBoil / 20;
        this.ingredientType = this.ingredient.ingredientType.toString();

        tooltip = new ArrayList<String>();
        tooltip.add(ingredientType);
        tooltip.add(String.format(StatCollector.translateToLocal("gui.text.potency"), String.valueOf(potency)));
        tooltip.add(String.format(StatCollector.translateToLocal("gui.text.instability"), String.valueOf(instability)));
        tooltip.add(String.format(StatCollector.translateToLocal("gui.text.boiling.time"), String.valueOf(timeToBoil) + "s"));
    }

    @Override
    public void onHoverOver(int mouseX, int mouseY) {
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
        if (isMouseOnWrapper(mouseX, mouseY) && hasKnowledge) {
            GuiHelper.drawItemStack(stack, mouseX + 3, mouseY);
            gui.drawHoveringText(tooltip, mouseX + 15, mouseY, renderer);
        }
    }

    @Override
    public boolean isMouseOnWrapper(int mouseX, int mouseY) {
        return GuiHelper.isMouseBetween(mouseX, mouseY, wrapperX, wrapperY, width, height);
    }
}
