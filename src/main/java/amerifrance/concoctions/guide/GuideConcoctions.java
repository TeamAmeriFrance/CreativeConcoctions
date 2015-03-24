package amerifrance.concoctions.guide;

import amerifrance.guideapi.GuideRegistry;
import amerifrance.guideapi.categories.CategoryItemStack;
import amerifrance.guideapi.objects.Book;
import amerifrance.guideapi.objects.abstraction.CategoryAbstract;
import amerifrance.guideapi.objects.abstraction.EntryAbstract;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.awt.*;
import java.util.ArrayList;

public class GuideConcoctions {

    public static Book concoctionBook;

    public static ArrayList<EntryAbstract> basicConcoctionEntries = new ArrayList<EntryAbstract>();
    public static ArrayList<EntryAbstract> compoundConcoctionEntries = new ArrayList<EntryAbstract>();

    public static void setConcoctionBook() {
        CategoryItemStack introductory = new CategoryItemStack(basicConcoctionEntries, "guide.creativeconcoctions.category.concoctions.intro", new ItemStack(Items.writable_book));
        CategoryItemStack basicConcoctions = new CategoryItemStack(basicConcoctionEntries, "guide.creativeconcoctions.category.concoctions.basic", new ItemStack(Items.glass_bottle));
        CategoryItemStack compoundConcoctions = new CategoryItemStack(compoundConcoctionEntries, "guide.creativeconcoctions.category.concoctions.compound", new ItemStack(Items.brewing_stand));
        ArrayList<CategoryAbstract> categories = new ArrayList<CategoryAbstract>();
        categories.add(introductory);
        categories.add(basicConcoctions);
        categories.add(compoundConcoctions);

        concoctionBook = new Book(categories, "guide.creativeconcoctions.title", "guide.creativeconcoctions.welcome", "guide.creativeconcoctions.title", new Color(216, 0, 197));
        GuideRegistry.registerBook(concoctionBook);
    }
}
