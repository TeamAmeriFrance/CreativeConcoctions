package amerifrance.concoctions.guide.property;

import amerifrance.concoctions.api.MapKey;
import amerifrance.concoctions.api.ingredients.IngredientProperties;
import amerifrance.concoctions.api.registry.IngredientsRegistry;
import amerifrance.concoctions.guide.property.PropertyWrapper;
import amerifrance.concoctions.guide.property.EntryProperty;
import amerifrance.guideapi.api.abstraction.CategoryAbstract;
import amerifrance.guideapi.api.base.Book;
import amerifrance.guideapi.buttons.ButtonBack;
import amerifrance.guideapi.buttons.ButtonNext;
import amerifrance.guideapi.buttons.ButtonPrev;
import amerifrance.guideapi.gui.GuiBase;
import amerifrance.guideapi.gui.GuiCategory;
import com.google.common.collect.HashMultimap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

import java.awt.*;

public class GuiProperty extends GuiBase {

    public ResourceLocation outlineTexture;
    public ResourceLocation pageTexture;
    public Book book;
    public CategoryAbstract category;
    public HashMultimap<Integer, PropertyWrapper> propertyWrapperMap;
    public EntryProperty entry;
    public ButtonBack buttonBack;
    public ButtonNext buttonNext;
    public ButtonPrev buttonPrev;
    public IngredientProperties properties;
    private int pageNumber;

    public GuiProperty(Book book, CategoryAbstract category, EntryProperty entry, EntityPlayer player, ItemStack bookStack) {
        super(player, bookStack);
        this.book = book;
        this.category = category;
        this.entry = entry;
        this.pageTexture = book.pageTexture;
        this.outlineTexture = book.outlineTexture;
        this.properties = entry.properties;
        this.propertyWrapperMap = propertyWrapperMap.create();
        this.pageNumber = 0;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.clear();
        this.propertyWrapperMap.clear();

        guiLeft = (this.width - this.xSize) / 2;
        guiTop = (this.height - this.ySize) / 2;

        this.buttonList.add(buttonBack = new ButtonBack(0, guiLeft + xSize / 6, guiTop, this));
        this.buttonList.add(buttonNext = new ButtonNext(1, guiLeft + 4 * xSize / 6, guiTop + 5 * ySize / 6, this));
        this.buttonList.add(buttonPrev = new ButtonPrev(2, guiLeft + xSize / 5, guiTop + 5 * ySize / 6, this));

        int eX = guiLeft + 37;
        int eY = guiTop + 15;
        int i = 0;
        int number = 0;
        for (MapKey key : IngredientsRegistry.getKeys()) {
            if (IngredientsRegistry.getIngredient(key.stack).getPropertiesList().contains(properties)) {
                propertyWrapperMap.put(number, new PropertyWrapper(eX, eY, 4 * xSize / 6, 10, player, this.fontRendererObj, key.stack, properties));
                eY += 13;
                i++;

                if (i >= 11) {
                    i = 0;
                    eY = guiTop + 15;
                    number++;
                }
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float renderPartialTicks) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(pageTexture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
        Minecraft.getMinecraft().getTextureManager().bindTexture(outlineTexture);
        drawTexturedModalRectWithColor(guiLeft, guiTop, 0, 0, xSize, ySize, book.bookColor);

        for (PropertyWrapper wrapper : propertyWrapperMap.get(pageNumber)) {
            if (wrapper.canPlayerSee()) {
                wrapper.draw(mouseX, mouseY, this);
                wrapper.drawExtras(mouseX, mouseY, this);
            }
            if (wrapper.isMouseOnWrapper(mouseX, mouseY) && wrapper.canPlayerSee()) {
                wrapper.onHoverOver(mouseX, mouseY);
            }
        }

        drawCenteredString(fontRendererObj, String.valueOf(pageNumber + 1) + "/" + String.valueOf(propertyWrapperMap.asMap().size()), guiLeft + xSize / 2, guiTop + 5 * ySize / 6, 0);
        drawCenteredString(fontRendererObj, entry.getLocalizedName(), guiLeft + xSize / 2, guiTop - 10, Color.WHITE.getRGB());
        super.drawScreen(mouseX, mouseY, renderPartialTicks);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int typeofClick) {
        super.mouseClicked(mouseX, mouseY, typeofClick);

        if (typeofClick == 1) {
            this.mc.displayGuiScreen(new GuiCategory(book, category, player, bookStack));
        }
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
        super.keyTyped(typedChar, keyCode);
        if (keyCode == Keyboard.KEY_BACK || keyCode == this.mc.gameSettings.keyBindUseItem.getKeyCode()) {
            this.mc.displayGuiScreen(new GuiCategory(book, category, player, bookStack));
        }

        if ((keyCode == Keyboard.KEY_UP || keyCode == Keyboard.KEY_RIGHT) && pageNumber + 1 < propertyWrapperMap.asMap().size()) {
            this.pageNumber++;
        }
        if ((keyCode == Keyboard.KEY_DOWN || keyCode == Keyboard.KEY_LEFT) && pageNumber > 0) {
            this.pageNumber--;
        }
    }

    @Override
    public void actionPerformed(GuiButton button) {
        if (button.id == 0) {
            this.mc.displayGuiScreen(new GuiCategory(book, category, player, bookStack));
        } else if (button.id == 1 && pageNumber + 1 < propertyWrapperMap.asMap().size()) {
            this.pageNumber++;
        } else if (button.id == 2 && pageNumber > 0) {
            this.pageNumber--;
        }
    }
}
