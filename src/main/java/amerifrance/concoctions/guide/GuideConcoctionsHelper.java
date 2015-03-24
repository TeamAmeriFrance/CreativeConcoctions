package amerifrance.concoctions.guide;

import amerifrance.concoctions.api.Concoction;
import amerifrance.guideapi.entries.EntryText;
import amerifrance.guideapi.objects.abstraction.IPage;
import amerifrance.guideapi.pages.PageIRecipe;
import amerifrance.guideapi.pages.PageUnlocImage;
import amerifrance.guideapi.pages.PageUnlocText;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

public class GuideConcoctionsHelper {

    /**
     * Used for basic concoctions.
     *
     * @param concoction - {@link amerifrance.concoctions.api.Concoction} to add an entry for
     * @param recipe - {@link net.minecraft.item.crafting.IRecipe} of the {@link amerifrance.concoctions.api.Concoction} to display
     */
    public static void addBasicConcoctionEntry(Concoction concoction, IRecipe recipe) {

        String unlocPrefix = "guide.creativeconcoctions." + concoction.name.toLowerCase().replace(" ", "") + ".";
        PageUnlocImage mainPage = new PageUnlocImage(unlocPrefix + "info.main", new ResourceLocation("minecraft:textures/items/potion_bottle_empty.png"), true);
        PageUnlocText descriptionPage = new PageUnlocText(unlocPrefix + "info.desc");

        PageIRecipe concoctionRecipe = new PageIRecipe(recipe);

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
     * @param concoction - {@link amerifrance.concoctions.api.Concoction} to add an entry for
     * @param recipe - {@link net.minecraft.item.crafting.IRecipe} of the {@link amerifrance.concoctions.api.Concoction} to display
     */
    public static void addCompoundConcoctionEntry(Concoction concoction, IRecipe recipe) {

        String unlocPrefix = "guide.creativeconcoctions." + concoction.name.toLowerCase().replace(" ", "") + ".";
        PageUnlocImage mainPage = new PageUnlocImage(unlocPrefix + "info.main", new ResourceLocation("minecraft:textures/items/potion_bottle_empty.png"), true);
        PageUnlocText descriptionPage = new PageUnlocText(unlocPrefix + "info.desc");
        PageUnlocText[] componentPage = new PageUnlocText[concoction.getComponents().length];
        for (int i = 0; i < concoction.getComponents().length; i++) { // Need to fix this so it prints all the component concoctions on the same page. Temporary solution: Different pages.
            componentPage[i] = new PageUnlocText(concoction.getComponents()[i].name);
        }

        PageIRecipe concoctionRecipe = new PageIRecipe(recipe);

        ArrayList<IPage> concoctionPages = new ArrayList<IPage>();
        concoctionPages.add(mainPage);
        concoctionPages.add(descriptionPage);
        for (int i = 0; i < componentPage.length; i++) { // See above comment
            concoctionPages.add(componentPage[i]);
        }
        concoctionPages.add(concoctionRecipe);

        EntryText concoctionEntry = new EntryText(concoctionPages, unlocPrefix + "entry");
        GuideConcoctions.compoundConcoctionEntries.add(concoctionEntry);
    }
}
