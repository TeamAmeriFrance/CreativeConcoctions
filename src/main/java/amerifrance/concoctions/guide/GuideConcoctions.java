package amerifrance.concoctions.guide;

import amerifrance.concoctions.ModInformation;
import amerifrance.concoctions.api.ingredients.IngredientProperty;
import amerifrance.concoctions.guide.property.EntryProperty;
import amerifrance.concoctions.registry.ItemsRegistry;
import amerifrance.guideapi.api.GuideRegistry;
import amerifrance.guideapi.api.abstraction.CategoryAbstract;
import amerifrance.guideapi.api.abstraction.EntryAbstract;
import amerifrance.guideapi.api.base.Book;
import amerifrance.guideapi.api.util.BookBuilder;
import amerifrance.guideapi.categories.CategoryItemStack;
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

        for (IngredientProperty ingredientProperty : IngredientProperty.values())
            ingredientKnowledgeEntries.add(new EntryProperty(ingredientProperty.getLocalizedString(), ingredientProperty));

        CategoryItemStack introductory = new CategoryItemStack(basicConcoctionEntries, "guide.creativeconcoctions.category.concoctions.intro", new ItemStack(Items.writable_book));
        CategoryItemStack basicConcoctions = new CategoryItemStack(basicConcoctionEntries, "guide.creativeconcoctions.category.concoctions.basic", new ItemStack(Items.glass_bottle));
        CategoryItemStack compoundConcoctions = new CategoryItemStack(compoundConcoctionEntries, "guide.creativeconcoctions.category.concoctions.compound", new ItemStack(Items.brewing_stand));
        CategoryItemStack ingredientKnowledge = new CategoryItemStack(ingredientKnowledgeEntries, "guide.creativeconcoctions.category.ingredient.knowledge", new ItemStack(ItemsRegistry.bottledIngredient));

        ArrayList<CategoryAbstract> categories = new ArrayList<CategoryAbstract>();
        categories.add(introductory);
        categories.add(basicConcoctions);
        categories.add(compoundConcoctions);
        categories.add(ingredientKnowledge);

        BookBuilder builder = new BookBuilder();
        builder.setUnlocBookTitle("guide.creativeconcoctions.title");
        builder.setUnlocWelcomeMessage("guide.creativeconcoctions.welcome");
        builder.setUnlocDisplayName("guide.creativeconcoctions.title");
        builder.setAuthor(ModInformation.NAME);
        builder.setCategories(categories);
        builder.setBookColor(new Color(216, 0, 197));
        concoctionBook = builder.build();
        GuideRegistry.registerBook(concoctionBook);
    }
}
