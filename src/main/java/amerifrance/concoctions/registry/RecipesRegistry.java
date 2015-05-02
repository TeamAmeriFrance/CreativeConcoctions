package amerifrance.concoctions.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class RecipesRegistry {

    public static void registerRecipes() {
        GameRegistry.addShapelessRecipe(new ItemStack(ItemsRegistry.ingredients, 1, 24), new ItemStack(Items.fish, 1, OreDictionary.WILDCARD_VALUE), new ItemStack(Items.bone));
    }
}
