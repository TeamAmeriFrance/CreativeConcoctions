package amerifrance.concoctions.api.registry;

import amerifrance.concoctions.api.util.MapKey;
import amerifrance.concoctions.api.ingredients.Ingredient;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public class IngredientsRegistry {

    private static HashMap<MapKey, Ingredient> registry = new HashMap<MapKey, Ingredient>();

    public static void registerIngredient(Ingredient ingredient, ItemStack stack) {
        MapKey key = MapKey.getKey(stack);
        if (registry.containsKey(key)) throw new IllegalArgumentException("Duplicate itemstack: " + stack);
        else registry.put(key, ingredient);
    }

    public static void registerIngredient(Ingredient ingredient, Item item) {
        registerIngredient(ingredient, new ItemStack(item));
    }

    public static void registerIngredient(Ingredient ingredient, Block block) {
        registerIngredient(ingredient, new ItemStack(block));
    }

    public static Ingredient getIngredient(ItemStack stack) {
        return registry.get(MapKey.getKey(stack));
    }

    public static Ingredient getIngredient(Item item) {
        return getIngredient(new ItemStack(item));
    }

    public static Ingredient getIngredient(Block block) {
        return getIngredient(new ItemStack(block));
    }

    public static boolean isMapEmtpy() {
        return registry.isEmpty();
    }

    public static int getMapSize() {
        return registry.size();
    }

    public static ArrayList<MapKey> getKeys() {
        return new ArrayList<MapKey>(registry.keySet());
    }

    public static ArrayList<Ingredient> getIngredients() {
        return new ArrayList<Ingredient>(registry.values());
    }

    public static MapKey getItemStack(Ingredient ingredient) {
        int index = getIngredients().indexOf(ingredient);
        return getKeys().get(index);
    }
}
