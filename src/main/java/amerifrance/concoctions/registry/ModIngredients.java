package amerifrance.concoctions.registry;

import amerifrance.concoctions.api.ingredients.Ingredient;
import amerifrance.concoctions.api.ingredients.IngredientProperties;
import amerifrance.concoctions.api.ingredients.IngredientType;
import amerifrance.concoctions.api.registry.IngredientsRegistry;
import cpw.mods.fml.common.registry.GameData;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ModIngredients {

    public static Ingredient water = new Ingredient(IngredientType.NEUTRAL, 0, 0, new IngredientProperties[]{IngredientProperties.WATER}, 150);

    public static void registerIngredients() {
        registerWaters();
        registerArmors();
        ir(Items.sugar, new Ingredient(IngredientType.PROCESSING_PRODUCT, 1, 2, new IngredientProperties[]{IngredientProperties.CATALYST, IngredientProperties.SPEED}, 50));
        ir(Items.firework_charge, new Ingredient(IngredientType.PROCESSING_PRODUCT, 8, 4, new IngredientProperties[]{IngredientProperties.CATALYST, IngredientProperties.SPEED, IngredientProperties.UNSTABLE}, 200));
        ir(Items.fireworks, new Ingredient(IngredientType.PROCESSING_PRODUCT, 12, 8, new IngredientProperties[]{IngredientProperties.CATALYST, IngredientProperties.SPEED, IngredientProperties.UNSTABLE, IngredientProperties.EXPLOSIVE}, 300));
        ir(new ItemStack(Items.skull, 1, 1), new Ingredient(IngredientType.MOB_DROP, 20, 8, new IngredientProperties[]{IngredientProperties.EVIL, IngredientProperties.DEMONIC}, 280));
    }

    public static void registerWaters() {
        ir(Items.water_bucket, water);
        ir(Blocks.water, water);
        ir(Blocks.flowing_water, water);
        ir(Items.potionitem, water);
    }

    public static void registerArmors() {
        for (Item item : GameData.getItemRegistry().typeSafeIterable()) {
            if (item instanceof ItemArmor) {
                ItemArmor armor = (ItemArmor) item;
                Ingredient protection = new Ingredient(IngredientType.NEUTRAL, 10, armor.getArmorMaterial().getDamageReductionAmount(armor.armorType), new IngredientProperties[]{IngredientProperties.PROTECTION, IngredientProperties.COOLANT}, 500);
                ir(armor, protection);
            }
        }
        Ingredient ironHorse = new Ingredient(IngredientType.NEUTRAL, 10, Items.iron_chestplate.getArmorMaterial().getDamageReductionAmount(Items.iron_chestplate.armorType) * 2, new IngredientProperties[]{IngredientProperties.PROTECTION, IngredientProperties.COOLANT}, 500);
        ir(Items.iron_horse_armor, ironHorse);
        Ingredient goldenHorse = new Ingredient(IngredientType.NEUTRAL, 10, Items.golden_chestplate.getArmorMaterial().getDamageReductionAmount(Items.golden_chestplate.armorType) * 2, new IngredientProperties[]{IngredientProperties.PROTECTION, IngredientProperties.COOLANT}, 500);
        ir(Items.golden_horse_armor, goldenHorse);
        Ingredient diamondHorse = new Ingredient(IngredientType.NEUTRAL, 10, Items.diamond_chestplate.getArmorMaterial().getDamageReductionAmount(Items.diamond_chestplate.armorType) * 2, new IngredientProperties[]{IngredientProperties.PROTECTION, IngredientProperties.COOLANT}, 500);
        ir(Items.diamond_horse_armor, diamondHorse);
    }

    private static void ir(Object o, Ingredient ingredient) {
        if (o instanceof ItemStack) IngredientsRegistry.registerIngredient(ingredient, (ItemStack) o);
        else if (o instanceof Item) IngredientsRegistry.registerIngredient(ingredient, (Item) o);
        else if (o instanceof Block) IngredientsRegistry.registerIngredient(ingredient, (Block) o);
        else throw new IllegalArgumentException("Not an ItemStack / Item / Block");
    }
}
