package amerifrance.concoctions.guide;

import amerifrance.concoctions.api.concoctions.Concoction;
import amerifrance.concoctions.guide.pages.PageConcoctionList;
import amerifrance.concoctions.guide.pages.PageConcoctionRecipe;
import amerifrance.guideapi.api.abstraction.IPage;
import amerifrance.guideapi.entries.EntryText;
import amerifrance.guideapi.pages.PageUnlocImage;
import amerifrance.guideapi.pages.PageUnlocText;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

public class GuideConcoctionsHelper {

    /**
     * Used for basic concoctions.
     *
     * @param concoction - {@link Concoction} to add an entry for
     */
    public static void addBasicConcoctionEntry(Concoction concoction) {

        String unlocPrefix = "guide.creativeconcoctions." + concoction.name.toLowerCase().replace(" ", "") + ".";
        PageUnlocImage mainPage = new PageUnlocImage(unlocPrefix + "info.main", new ResourceLocation("minecraft:textures/items/potion_bottle_empty.png"), true);
        PageUnlocText descriptionPage = new PageUnlocText(unlocPrefix + "info.desc");
        PageConcoctionRecipe concoctionRecipe = new PageConcoctionRecipe(concoction);

        ArrayList<IPage> concoctionPages = new ArrayList<IPage>();
        concoctionPages.add(mainPage);
        concoctionPages.add(descriptionPage);
        concoctionPages.add(concoctionRecipe);

        EntryText concoctionEntry = new EntryText(concoctionPages, unlocPrefix + "entry");
        GuideConcoctions.basicConcoctionEntries.add(concoctionEntry);
    }

    /**
     * Used for Compound Concoctions. Adds extra informational pages.
     *
     * @param concoction - {@link Concoction} to add an entry for
     */
    public static void addCompoundConcoctionEntry(Concoction concoction) {

        String unlocPrefix = "guide.creativeconcoctions." + concoction.name.toLowerCase().replace(" ", "") + ".";
        PageUnlocImage mainPage = new PageUnlocImage(unlocPrefix + "info.main", new ResourceLocation("minecraft:textures/items/potion_bottle_empty.png"), true);
        PageUnlocText descriptionPage = new PageUnlocText(unlocPrefix + "info.desc");
        PageConcoctionList componentPage = new PageConcoctionList("guide.creativeconcoctions.page.componentlist.title", concoction.getComponents());
        PageConcoctionRecipe concoctionRecipe = new PageConcoctionRecipe(concoction);

        ArrayList<IPage> concoctionPages = new ArrayList<IPage>();
        concoctionPages.add(mainPage);
        concoctionPages.add(descriptionPage);
        concoctionPages.add(componentPage);
        concoctionPages.add(concoctionRecipe);

        EntryText concoctionEntry = new EntryText(concoctionPages, unlocPrefix + "entry");
        GuideConcoctions.compoundConcoctionEntries.add(concoctionEntry);
    }
}
