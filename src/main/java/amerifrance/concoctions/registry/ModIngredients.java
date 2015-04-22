package amerifrance.concoctions.registry;

import amerifrance.concoctions.api.CreativeConcoctionsAPI;
import amerifrance.concoctions.api.ingredients.Ingredient;
import amerifrance.concoctions.api.ingredients.IngredientProperty;
import amerifrance.concoctions.api.ingredients.IngredientType;
import amerifrance.concoctions.api.registry.IngredientsRegistry;
import cpw.mods.fml.common.registry.GameData;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.*;

public class ModIngredients {

    public static Ingredient water = new Ingredient(IngredientType.NEUTRAL, 0, 0, 150, IngredientProperty.WATER);

    public static void registerIngredients() {
        registerWaters();
        registerArmors();
        registerSwords();
        registerTools();
        ir(Items.sugar, new Ingredient(IngredientType.PROCESSING_PRODUCT, 1, 2, 50, IngredientProperty.CATALYST, IngredientProperty.SPEED));
        ir(Items.firework_charge, new Ingredient(IngredientType.PROCESSING_PRODUCT, 8, 4, 200, IngredientProperty.CATALYST, IngredientProperty.SPEED, IngredientProperty.UNSTABLE));
        ir(Items.fireworks, new Ingredient(IngredientType.PROCESSING_PRODUCT, 12, 8, 300, IngredientProperty.CATALYST, IngredientProperty.SPEED, IngredientProperty.UNSTABLE, IngredientProperty.EXPLOSIVE));
        ir(new ItemStack(Items.skull, 1, 1), new Ingredient(IngredientType.MOB_DROP, 20, 8, 280, IngredientProperty.EVIL, IngredientProperty.DEMONIC));
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
                Ingredient protection = new Ingredient(IngredientType.NEUTRAL, 10, armor.getArmorMaterial().getDamageReductionAmount(armor.armorType), 500, IngredientProperty.PROTECTION, IngredientProperty.COOLANT);
                ir(armor, protection);
            }
        }
        Ingredient ironHorse = new Ingredient(IngredientType.NEUTRAL, 10, Items.iron_chestplate.getArmorMaterial().getDamageReductionAmount(Items.iron_chestplate.armorType) * 2, 500, IngredientProperty.PROTECTION, IngredientProperty.COOLANT);
        ir(Items.iron_horse_armor, ironHorse);
        Ingredient goldenHorse = new Ingredient(IngredientType.NEUTRAL, 10, Items.golden_chestplate.getArmorMaterial().getDamageReductionAmount(Items.golden_chestplate.armorType) * 2, 500, IngredientProperty.PROTECTION, IngredientProperty.COOLANT);
        ir(Items.golden_horse_armor, goldenHorse);
        Ingredient diamondHorse = new Ingredient(IngredientType.NEUTRAL, 10, Items.diamond_chestplate.getArmorMaterial().getDamageReductionAmount(Items.diamond_chestplate.armorType) * 2, 500, IngredientProperty.PROTECTION, IngredientProperty.COOLANT);
        ir(Items.diamond_horse_armor, diamondHorse);
    }

    public static void registerSwords() {
        for (Item item : GameData.getItemRegistry().typeSafeIterable()) {
            if (item instanceof ItemSword) {
                ItemSword sword = (ItemSword) item;
                Ingredient attack = new Ingredient(IngredientType.PROCESSING_PRODUCT, 8, (int) sword.func_150931_i(), 400, IngredientProperty.ATTACK, IngredientProperty.DAMAGE);
                ir(sword, attack);
            }
        }
    }

    public static void registerTools() {
        for (Item item : GameData.getItemRegistry().typeSafeIterable()) {
            if (item instanceof ItemTool) {
                ItemTool tool = (ItemTool) item;
                Ingredient harvest = new Ingredient(IngredientType.PROCESSING_PRODUCT, 8, getToolPotency(tool), 400, IngredientProperty.EARTH, IngredientProperty.STRENGTH);
                ir(tool, harvest);
            }
        }
    }

    static int getToolPotency(ItemTool tool) {
        ItemStack toolStack = new ItemStack(tool);
        if (tool instanceof ItemPickaxe) return (int) tool.func_150893_a(toolStack, Blocks.stone);
        if (tool instanceof ItemSpade) return (int) tool.func_150893_a(toolStack, Blocks.dirt);
        if (tool instanceof ItemAxe) return (int) tool.func_150893_a(toolStack, Blocks.planks);
        else
            return CreativeConcoctionsAPI.average((int) tool.func_150893_a(toolStack, Blocks.dirt), (int) tool.func_150893_a(toolStack, Blocks.stone), (int) tool.func_150893_a(toolStack, Blocks.planks));
    }

    private static void ir(Object o, Ingredient ingredient) {
        if (o instanceof ItemStack) IngredientsRegistry.registerIngredient(ingredient, (ItemStack) o);
        else if (o instanceof Item) IngredientsRegistry.registerIngredient(ingredient, (Item) o);
        else if (o instanceof Block) IngredientsRegistry.registerIngredient(ingredient, (Block) o);
        else throw new IllegalArgumentException("Not an ItemStack / Item / Block");
    }
}
