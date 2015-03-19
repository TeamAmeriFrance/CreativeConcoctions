package amerifrance.concoctions.guide;

import amerifrance.concoctions.api.Concoction;
import amerifrance.concoctions.concoctions.ModConcoctions;
import amerifrance.guideapi.GuideRegistry;
import amerifrance.guideapi.categories.CategoryItemStack;
import amerifrance.guideapi.entries.EntryText;
import amerifrance.guideapi.objects.Book;
import amerifrance.guideapi.objects.abstraction.CategoryAbstract;
import amerifrance.guideapi.objects.abstraction.EntryAbstract;
import amerifrance.guideapi.objects.abstraction.IPage;
import amerifrance.guideapi.pages.PageIRecipe;
import amerifrance.guideapi.pages.PageUnlocImage;
import amerifrance.guideapi.pages.PageUnlocText;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.awt.*;
import java.util.ArrayList;

public class GuideConcoctions {

    public static Book concoctionBook;

    public static ArrayList<EntryAbstract> concoctionEntries = new ArrayList<EntryAbstract>();

    public static void setConcoctionBook() {

        addConcoctionEntry(ModConcoctions.speed, new ShapelessOreRecipe(new ItemStack(Items.blaze_powder, 3, 0), Items.blaze_rod));
        addConcoctionEntry(ModConcoctions.hellEyes, new ShapelessOreRecipe(new ItemStack(Items.ender_pearl), Items.apple));

        CategoryItemStack concoctions = new CategoryItemStack(concoctionEntries, "guide.creativeconcoctions.category.concoctions", new ItemStack(Items.glass_bottle));
        ArrayList<CategoryAbstract> categories = new ArrayList<CategoryAbstract>();
        categories.add(concoctions);

        concoctionBook = new Book(categories, "guide.creativeconcoctions.title", "guide.creativeconcoctions.welcome", "guide.creativeconcoctions.title", new Color(216, 0, 197));
        GuideRegistry.registerBook(concoctionBook);
    }

    public static void addConcoctionEntry(Concoction concoction, IRecipe recipe) {

        String unlocPrefix = "guide.creativeconcoctions." + concoction.name.toLowerCase().replace(" ", "") + ".";
        PageUnlocImage mainPage = new PageUnlocImage(unlocPrefix + "info.main", new ResourceLocation("minecraft:textures/items/potion_bottle_empty.png"), true);
        PageUnlocText descriptionPage = new PageUnlocText(unlocPrefix + "info.desc");
        PageIRecipe concoctionRecipe = new PageIRecipe(recipe);

        ArrayList<IPage> concoctionPages = new ArrayList<IPage>();
        concoctionPages.add(mainPage);
        concoctionPages.add(descriptionPage);
        concoctionPages.add(concoctionRecipe);

        EntryText concoctionEntry = new EntryText(concoctionPages, unlocPrefix + "entry");
        concoctionEntries.add(concoctionEntry);
    }
}
