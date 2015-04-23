package amerifrance.concoctions.registry;

import amerifrance.concoctions.api.ingredients.IngredientProperty;
import amerifrance.concoctions.api.registry.ConcoctionRecipes;
import amerifrance.concoctions.api.registry.ConcoctionsRegistry;
import amerifrance.concoctions.concoctions.basic.*;
import amerifrance.concoctions.concoctions.compound.ConcoctionSaiyanSerum;
import amerifrance.concoctions.concoctions.compound.ConcoctionVenomousVigor;
import com.google.common.collect.Lists;

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
    public static ConcoctionKnowledge knowledge;

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

        knowledge = new ConcoctionKnowledge();
        ConcoctionsRegistry.registerConcoction(knowledge, "ConcoctionsKnowledge");
    }

    public static void registerCompoundConcoctions() {
        venomousVigor = new ConcoctionVenomousVigor();
        ConcoctionsRegistry.registerConcoction(venomousVigor, "ConcoctionVenomousVigor");

        saiyanSerum = new ConcoctionSaiyanSerum();
        ConcoctionsRegistry.registerConcoction(saiyanSerum, "ConcoctionSaiyanSerum");
    }

    public static void registerConcoctionRecipes() {
        ConcoctionRecipes.addRecipe(Lists.newArrayList(IngredientProperty.STRENGTH, IngredientProperty.SPEED, IngredientProperty.FLIGHT, IngredientProperty.SLOWING_DOWN, IngredientProperty.PROTECTION), speed);
        ConcoctionRecipes.addRecipe(Lists.newArrayList(IngredientProperty.STRENGTH, IngredientProperty.ATTACK, IngredientProperty.DAMAGE, IngredientProperty.EARTH, IngredientProperty.ENERGY), strength);
        ConcoctionRecipes.addRecipe(Lists.newArrayList(IngredientProperty.ATTACK, IngredientProperty.DAMAGE, IngredientProperty.EVIL, IngredientProperty.MAGIC, IngredientProperty.DEMONIC, IngredientProperty.AIR, IngredientProperty.ATTACK), hellEyes);
        ConcoctionRecipes.addRecipe(Lists.newArrayList(IngredientProperty.SPEED, IngredientProperty.SLOWING_DOWN, IngredientProperty.SLOWING_DOWN, IngredientProperty.GRAVITY), slowness);
        ConcoctionRecipes.addRecipe(Lists.newArrayList(IngredientProperty.HEALING, IngredientProperty.MAGIC, IngredientProperty.REGENERATION, IngredientProperty.MAGIC), regeneration);
        ConcoctionRecipes.addRecipe(Lists.newArrayList(IngredientProperty.HEALING, IngredientProperty.MAGIC), heal);
        ConcoctionRecipes.addRecipe(Lists.newArrayList(IngredientProperty.ATTACK, IngredientProperty.DAMAGE), damage);
        ConcoctionRecipes.addRecipe(Lists.newArrayList(IngredientProperty.FIRE, IngredientProperty.ENERGY, IngredientProperty.DAMAGE, IngredientProperty.PROTECTION), fireProtection);
        ConcoctionRecipes.addRecipe(Lists.newArrayList(IngredientProperty.AIR, IngredientProperty.FLIGHT, IngredientProperty.GRAVITY, IngredientProperty.STRENGTH), jumpBoost);
        ConcoctionRecipes.addRecipe(Lists.newArrayList(IngredientProperty.FIRE, IngredientProperty.AIR, IngredientProperty.EXPLOSIVE, IngredientProperty.MAGIC, IngredientProperty.ATTACK), fireball);
        ConcoctionRecipes.addRecipe(Lists.newArrayList(IngredientProperty.AIR, IngredientProperty.FLIGHT, IngredientProperty.GRAVITY, IngredientProperty.SLOWING_DOWN), featherFall);
        ConcoctionRecipes.addRecipe(Lists.newArrayList(IngredientProperty.PROTECTION, IngredientProperty.MAGIC, IngredientProperty.PROTECTION, IngredientProperty.STRENGTH), resistance);
        ConcoctionRecipes.addRecipe(Lists.newArrayList(IngredientProperty.SLOWING_DOWN, IngredientProperty.MAGIC, IngredientProperty.POISON), mineSlow);
        ConcoctionRecipes.addRecipe(Lists.newArrayList(IngredientProperty.SPEED, IngredientProperty.MAGIC, IngredientProperty.STRENGTH, IngredientProperty.ENERGY), mineFast);
        ConcoctionRecipes.addRecipe(Lists.newArrayList(IngredientProperty.SPEED, IngredientProperty.MAGIC, IngredientProperty.ENERGY, IngredientProperty.MAGIC, IngredientProperty.SWIMMING), swim);
        ConcoctionRecipes.addRecipe(Lists.newArrayList(IngredientProperty.MAGIC, IngredientProperty.POISON, IngredientProperty.DAMAGE, IngredientProperty.POISON, IngredientProperty.DAMAGE), poison);
        ConcoctionRecipes.addRecipe(Lists.newArrayList(IngredientProperty.NOURISHMENT, IngredientProperty.DAMAGE, IngredientProperty.NOURISHMENT, IngredientProperty.POISON), hunger);
    }
}
