package amerifrance.concoctions.registry;

import amerifrance.concoctions.api.concoctions.Concoction;
import amerifrance.concoctions.api.ingredients.IngredientProperties;
import amerifrance.concoctions.api.registry.ConcoctionRecipes;
import amerifrance.concoctions.api.registry.ConcoctionsRegistry;
import amerifrance.concoctions.concoctions.basic.*;
import amerifrance.concoctions.concoctions.compound.ConcoctionSaiyanSerum;
import amerifrance.concoctions.concoctions.compound.ConcoctionVenomousVigor;
import amerifrance.concoctions.guide.GuideConcoctionsHelper;
import com.google.common.collect.Lists;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ModConcoctions {

    //Basics
    public static Concoction speed;
    public static Concoction strength;
    public static Concoction hellEyes;
    public static Concoction slowness;
    public static Concoction regeneration;
    public static Concoction heal;
    public static Concoction damage;
    public static Concoction fireProtection;
    public static Concoction jumpBoost;
    public static Concoction cleanse;
    public static Concoction fireball;
    public static Concoction featherFall;
    public static Concoction resistance;
    public static Concoction mineSlow;
    public static Concoction mineFast;
    public static Concoction swim;
    public static Concoction poison;
    public static Concoction hunger;
    public static Concoction luman;

    //Compounds
    public static Concoction venomousVigor;
    public static Concoction saiyanSerum;

    public static void registerBasicConcoctions() {
        speed = new ConcoctionSpeed();
        ConcoctionsRegistry.registerConcoction(speed, "ConcoctionSpeed");
        GuideConcoctionsHelper.addBasicConcoctionEntry(speed, new ShapelessOreRecipe(new ItemStack(Items.blaze_powder, 3, 0), Items.blaze_rod));

        strength = new ConcoctionStrength();
        ConcoctionsRegistry.registerConcoction(strength, "ConcoctionStrength");
        GuideConcoctionsHelper.addBasicConcoctionEntry(strength, new ShapelessOreRecipe(new ItemStack(Items.blaze_powder, 3, 0), Items.blaze_rod));

        hellEyes = new ConcoctionHellEyes();
        ConcoctionsRegistry.registerConcoction(hellEyes, "ConcoctionHellEyes");
        GuideConcoctionsHelper.addBasicConcoctionEntry(hellEyes, new ShapelessOreRecipe(new ItemStack(Items.blaze_powder, 3, 0), Items.blaze_rod));

        slowness = new ConcoctionSlowness();
        ConcoctionsRegistry.registerConcoction(slowness, "ConcoctionSlowness");
        GuideConcoctionsHelper.addBasicConcoctionEntry(slowness, new ShapelessOreRecipe(new ItemStack(Items.blaze_powder, 3, 0), Items.blaze_rod));

        regeneration = new ConcoctionRegeneration();
        ConcoctionsRegistry.registerConcoction(regeneration, "ConcoctionRegeneration");
        GuideConcoctionsHelper.addBasicConcoctionEntry(regeneration, new ShapelessOreRecipe(new ItemStack(Items.blaze_powder, 3, 0), Items.blaze_rod));

        heal = new ConcoctionHeal();
        ConcoctionsRegistry.registerConcoction(heal, "ConcoctionHeal");
        GuideConcoctionsHelper.addBasicConcoctionEntry(heal, new ShapelessOreRecipe(new ItemStack(Items.blaze_powder, 3, 0), Items.blaze_rod));

        damage = new ConcoctionDamage();
        ConcoctionsRegistry.registerConcoction(damage, "ConcoctionDamage");
        GuideConcoctionsHelper.addBasicConcoctionEntry(damage, new ShapelessOreRecipe(new ItemStack(Items.blaze_powder, 3, 0), Items.blaze_rod));

        fireProtection = new ConcoctionFireProtection();
        ConcoctionsRegistry.registerConcoction(fireProtection, "ConcoctionFireProtection");
        GuideConcoctionsHelper.addBasicConcoctionEntry(fireProtection, new ShapelessOreRecipe(new ItemStack(Items.blaze_powder, 3, 0), Items.blaze_rod));

        jumpBoost = new ConcoctionJumpBoost();
        ConcoctionsRegistry.registerConcoction(jumpBoost, "ConcoctionJumpBoost");
        GuideConcoctionsHelper.addBasicConcoctionEntry(jumpBoost, new ShapelessOreRecipe(new ItemStack(Items.blaze_powder, 3, 0), Items.blaze_rod));

        cleanse = new ConcoctionCleanse();
        ConcoctionsRegistry.registerConcoction(cleanse, "ConcoctionCleanse");
        GuideConcoctionsHelper.addBasicConcoctionEntry(cleanse, new ShapelessOreRecipe(new ItemStack(Items.blaze_powder, 3, 0), Items.blaze_rod));

        fireball = new ConcoctionFireball();
        ConcoctionsRegistry.registerConcoction(fireball, "ConcoctionFireball");
        GuideConcoctionsHelper.addBasicConcoctionEntry(fireball, new ShapelessOreRecipe(new ItemStack(Items.blaze_powder, 3, 0), Items.blaze_rod));

        featherFall = new ConcoctionFeatherFall();
        ConcoctionsRegistry.registerConcoction(featherFall, "ConcoctionFeatherFall");
        GuideConcoctionsHelper.addBasicConcoctionEntry(featherFall, new ShapelessOreRecipe(new ItemStack(Items.blaze_powder, 3, 0), Items.blaze_rod));

        resistance = new ConcoctionResistance();
        ConcoctionsRegistry.registerConcoction(resistance, "ConcoctionResistance");
        GuideConcoctionsHelper.addBasicConcoctionEntry(resistance, new ShapelessOreRecipe(new ItemStack(Items.blaze_powder, 3, 0), Items.blaze_rod));

        mineSlow = new ConcoctionMineSlow();
        ConcoctionsRegistry.registerConcoction(mineSlow, "ConcoctionMineSlow");
        GuideConcoctionsHelper.addBasicConcoctionEntry(mineSlow, new ShapelessOreRecipe(new ItemStack(Items.blaze_powder, 3, 0), Items.blaze_rod));

        mineFast = new ConcoctionMineFast();
        ConcoctionsRegistry.registerConcoction(mineFast, "ConcoctionMineFast");
        GuideConcoctionsHelper.addBasicConcoctionEntry(mineFast, new ShapelessOreRecipe(new ItemStack(Items.blaze_powder, 3, 0), Items.blaze_rod));

        swim = new ConcoctionSwim();
        ConcoctionsRegistry.registerConcoction(swim, "ConcoctionSwim");
        GuideConcoctionsHelper.addBasicConcoctionEntry(swim, new ShapelessOreRecipe(new ItemStack(Items.blaze_powder, 3, 0), Items.blaze_rod));

        poison = new ConcoctionPoison();
        ConcoctionsRegistry.registerConcoction(poison, "ConcoctionPoison");
        GuideConcoctionsHelper.addBasicConcoctionEntry(poison, new ShapelessOreRecipe(new ItemStack(Items.blaze_powder, 3, 0), Items.blaze_rod));

        hunger = new ConcoctionHunger();
        ConcoctionsRegistry.registerConcoction(hunger, "ConcoctionHunger");
        GuideConcoctionsHelper.addBasicConcoctionEntry(hunger, new ShapelessOreRecipe(new ItemStack(Items.blaze_powder, 3, 0), Items.blaze_rod));

        luman = new ConcoctionLight();
        ConcoctionsRegistry.registerConcoction(luman, "ConcoctionLumanLiquor");
        GuideConcoctionsHelper.addBasicConcoctionEntry(luman, new ShapelessOreRecipe(new ItemStack(Items.blaze_powder, 3, 0), Items.blaze_rod));
    }

    public static void registerCompoundConcoctions() {
        venomousVigor = new ConcoctionVenomousVigor();
        ConcoctionsRegistry.registerConcoction(venomousVigor, "ConcoctionVenomousVigor");
        GuideConcoctionsHelper.addCompoundConcoctionEntry(venomousVigor, new ShapelessOreRecipe(new ItemStack(Items.blaze_powder, 3, 0), Items.blaze_rod));

        saiyanSerum = new ConcoctionSaiyanSerum();
        ConcoctionsRegistry.registerConcoction(saiyanSerum, "ConcoctionSaiyanSerum");
        GuideConcoctionsHelper.addCompoundConcoctionEntry(saiyanSerum, new ShapelessOreRecipe(new ItemStack(Items.blaze_powder, 3, 0), Items.blaze_rod));
    }

    public static void registerConcoctionRecipes() {
        ConcoctionRecipes.addRecipe(Lists.newArrayList(IngredientProperties.STRENGTH, IngredientProperties.SPEED, IngredientProperties.FLIGHT, IngredientProperties.SLOWING_DOWN, IngredientProperties.PROTECTION), speed);
        ConcoctionRecipes.addRecipe(Lists.newArrayList(IngredientProperties.STRENGTH, IngredientProperties.ATTACK, IngredientProperties.DAMAGE, IngredientProperties.EARTH, IngredientProperties.ENERGY), strength);
        ConcoctionRecipes.addRecipe(Lists.newArrayList(IngredientProperties.ATTACK, IngredientProperties.DAMAGE, IngredientProperties.EVIL, IngredientProperties.MAGIC, IngredientProperties.DEMONIC, IngredientProperties.AIR, IngredientProperties.ATTACK), hellEyes);
        ConcoctionRecipes.addRecipe(Lists.newArrayList(IngredientProperties.SPEED, IngredientProperties.SLOWING_DOWN, IngredientProperties.SLOWING_DOWN, IngredientProperties.GRAVITY), slowness);
        ConcoctionRecipes.addRecipe(Lists.newArrayList(IngredientProperties.HEALING, IngredientProperties.MAGIC, IngredientProperties.REGENERATION, IngredientProperties.MAGIC), regeneration);
        ConcoctionRecipes.addRecipe(Lists.newArrayList(IngredientProperties.HEALING, IngredientProperties.MAGIC), heal);
        ConcoctionRecipes.addRecipe(Lists.newArrayList(IngredientProperties.ATTACK, IngredientProperties.DAMAGE), damage);
        ConcoctionRecipes.addRecipe(Lists.newArrayList(IngredientProperties.FIRE, IngredientProperties.ENERGY, IngredientProperties.DAMAGE, IngredientProperties.PROTECTION), fireProtection);
        ConcoctionRecipes.addRecipe(Lists.newArrayList(IngredientProperties.AIR, IngredientProperties.FLIGHT, IngredientProperties.GRAVITY, IngredientProperties.STRENGTH), jumpBoost);
        ConcoctionRecipes.addRecipe(Lists.newArrayList(IngredientProperties.FIRE, IngredientProperties.AIR, IngredientProperties.EXPLOSIVE, IngredientProperties.MAGIC, IngredientProperties.ATTACK), fireball);
        ConcoctionRecipes.addRecipe(Lists.newArrayList(IngredientProperties.AIR, IngredientProperties.FLIGHT, IngredientProperties.GRAVITY, IngredientProperties.SLOWING_DOWN), featherFall);
        ConcoctionRecipes.addRecipe(Lists.newArrayList(IngredientProperties.PROTECTION, IngredientProperties.MAGIC, IngredientProperties.PROTECTION, IngredientProperties.STRENGTH), resistance);
        ConcoctionRecipes.addRecipe(Lists.newArrayList(IngredientProperties.SLOWING_DOWN, IngredientProperties.MAGIC, IngredientProperties.POISON), mineSlow);
        ConcoctionRecipes.addRecipe(Lists.newArrayList(IngredientProperties.SPEED, IngredientProperties.MAGIC, IngredientProperties.STRENGTH, IngredientProperties.ENERGY), mineFast);
        ConcoctionRecipes.addRecipe(Lists.newArrayList(IngredientProperties.SPEED, IngredientProperties.MAGIC, IngredientProperties.ENERGY, IngredientProperties.MAGIC, IngredientProperties.SWIMMING), swim);
        ConcoctionRecipes.addRecipe(Lists.newArrayList(IngredientProperties.MAGIC, IngredientProperties.POISON, IngredientProperties.DAMAGE, IngredientProperties.POISON, IngredientProperties.DAMAGE), poison);
        ConcoctionRecipes.addRecipe(Lists.newArrayList(IngredientProperties.NOURISHMENT, IngredientProperties.DAMAGE, IngredientProperties.NOURISHMENT, IngredientProperties.POISON), hunger);
    }
}
