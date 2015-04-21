package amerifrance.concoctions.guide;

import amerifrance.concoctions.api.ingredients.IngredientProperties;
import amerifrance.concoctions.registry.ItemsRegistry;
import amerifrance.guideapi.api.GuideRegistry;
import amerifrance.guideapi.api.abstraction.CategoryAbstract;
import amerifrance.guideapi.api.abstraction.EntryAbstract;
import amerifrance.guideapi.api.abstraction.IPage;
import amerifrance.guideapi.api.base.Book;
import amerifrance.guideapi.categories.CategoryItemStack;
import amerifrance.guideapi.entries.EntryText;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.awt.*;
import java.util.ArrayList;

public class GuideConcoctions {

    public static Book concoctionBook;

    public static ArrayList<EntryAbstract> basicConcoctionEntries = new ArrayList<EntryAbstract>();
    public static ArrayList<EntryAbstract> compoundConcoctionEntries = new ArrayList<EntryAbstract>();
    public static ArrayList<EntryAbstract> ingredientKnowledgeEntries = new ArrayList<EntryAbstract>();

    public static void setConcoctionBook() {

        for (IngredientProperties properties : IngredientProperties.values()) {
            ArrayList<IPage> list = new ArrayList<IPage>();
            list.add(new PageIngredientProperty(properties));
            ingredientKnowledgeEntries.add(new EntryText(list, properties.name()));
        }

        CategoryItemStack introductory = new CategoryItemStack(basicConcoctionEntries, "guide.creativeconcoctions.category.concoctions.intro", new ItemStack(Items.writable_book));
        CategoryItemStack basicConcoctions = new CategoryItemStack(basicConcoctionEntries, "guide.creativeconcoctions.category.concoctions.basic", new ItemStack(Items.glass_bottle));
        CategoryItemStack compoundConcoctions = new CategoryItemStack(compoundConcoctionEntries, "guide.creativeconcoctions.category.concoctions.compound", new ItemStack(Items.brewing_stand));
        CategoryItemStack ingredientKnowledge = new CategoryItemStack(ingredientKnowledgeEntries, "guide.creativeconcoctions.category.ingredient.knowledge", new ItemStack(ItemsRegistry.bottledIngredient));

        ArrayList<CategoryAbstract> categories = new ArrayList<CategoryAbstract>();
        categories.add(introductory);
        categories.add(basicConcoctions);
        categories.add(compoundConcoctions);
        categories.add(ingredientKnowledge);

        concoctionBook = new Book(categories, "guide.creativeconcoctions.title", "guide.creativeconcoctions.welcome", "guide.creativeconcoctions.title", new Color(216, 0, 197));
        GuideRegistry.registerBook(concoctionBook);
    }
}
