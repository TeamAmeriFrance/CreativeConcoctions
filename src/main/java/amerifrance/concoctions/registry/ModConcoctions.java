package amerifrance.concoctions.registry;

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
    public static ConcoctionSpeed speed;
    public static ConcoctionStrength strength;
    public static ConcoctionHellEyes hellEyes;
    public static ConcoctionSlowness slowness;
    public static ConcoctionRegeneration regeneration;
    public static ConcoctionHeal heal;
    public static ConcoctionDamage damage;
    public static ConcoctionFireProtection fireProtection;
    public static ConcoctionJumpBoost jumpBoost;
    public static ConcoctionCleanse cleanse;
    public static ConcoctionFireball fireball;
    public static ConcoctionFeatherFall featherFall;
    public static ConcoctionResistance resistance;
    public static ConcoctionMineSlow mineSlow;
    public static ConcoctionMineFast mineFast;
    public static ConcoctionSwim swim;
    public static ConcoctionPoison poison;
    public static ConcoctionHunger hunger;
    public static ConcoctionLight lumanLiquor;

    //Compounds
    public static ConcoctionVenomousVigor venomousVigor;
    public static ConcoctionSaiyanSerum saiyanSerum;

    public static void registerBasicConcoctions() {
        speed = new ConcoctionSpeed();
        ConcoctionsRegistry.registerConcoction(speed, "ConcoctionSpeed");

        strength = new ConcoctionStrength();
        ConcoctionsRegistry.registerConcoction(strength, "ConcoctionStrength");

        hellEyes = new ConcoctionHellEyes();
        ConcoctionsRegistry.registerConcoction(hellEyes, "ConcoctionHellEyes");

        slowness = new ConcoctionSlowness();
        ConcoctionsRegistry.registerConcoction(slowness, "ConcoctionSlowness");

        regeneration = new ConcoctionRegeneration();
        ConcoctionsRegistry.registerConcoction(regeneration, "ConcoctionRegeneration");

        heal = new ConcoctionHeal();
        ConcoctionsRegistry.registerConcoction(heal, "ConcoctionHeal");

        damage = new ConcoctionDamage();
        ConcoctionsRegistry.registerConcoction(damage, "ConcoctionDamage");

        fireProtection = new ConcoctionFireProtection();
        ConcoctionsRegistry.registerConcoction(fireProtection, "ConcoctionFireProtection");

        jumpBoost = new ConcoctionJumpBoost();
        ConcoctionsRegistry.registerConcoction(jumpBoost, "ConcoctionJumpBoost");

        cleanse = new ConcoctionCleanse();
        ConcoctionsRegistry.registerConcoction(cleanse, "ConcoctionCleanse");

        fireball = new ConcoctionFireball();
        ConcoctionsRegistry.registerConcoction(fireball, "ConcoctionFireball");

        featherFall = new ConcoctionFeatherFall();
        ConcoctionsRegistry.registerConcoction(featherFall, "ConcoctionFeatherFall");

        resistance = new ConcoctionResistance();
        ConcoctionsRegistry.registerConcoction(resistance, "ConcoctionResistance");

        mineSlow = new ConcoctionMineSlow();
        ConcoctionsRegistry.registerConcoction(mineSlow, "ConcoctionMineSlow");

        mineFast = new ConcoctionMineFast();
        ConcoctionsRegistry.registerConcoction(mineFast, "ConcoctionMineFast");

        swim = new ConcoctionSwim();
        ConcoctionsRegistry.registerConcoction(swim, "ConcoctionSwim");

        poison = new ConcoctionPoison();
        ConcoctionsRegistry.registerConcoction(poison, "ConcoctionPoison");

        hunger = new ConcoctionHunger();
        ConcoctionsRegistry.registerConcoction(hunger, "ConcoctionHunger");

        lumanLiquor = new ConcoctionLight();
        ConcoctionsRegistry.registerConcoction(lumanLiquor, "ConcoctionLumanLiquor");
    }

    public static void registerCompoundConcoctions() {
        venomousVigor = new ConcoctionVenomousVigor();
        ConcoctionsRegistry.registerConcoction(venomousVigor, "ConcoctionVenomousVigor");

        saiyanSerum = new ConcoctionSaiyanSerum();
        ConcoctionsRegistry.registerConcoction(saiyanSerum, "ConcoctionSaiyanSerum");
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
