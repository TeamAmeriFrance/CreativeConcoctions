package amerifrance.concoctions.api.registry;

import amerifrance.concoctions.api.ingredients.Ingredient;
import amerifrance.concoctions.util.MapKey;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class IngredientsRegistry {

    private static BiMap<MapKey, Ingredient> registry = HashBiMap.create();

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

    public static ItemStack getItemStack(Ingredient ingredient) {
        return registry.inverse().get(ingredient).stack;
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

    public static ArrayList<Ingredient> getConcoctions() {
        return new ArrayList<Ingredient>(registry.values());
    }
}
