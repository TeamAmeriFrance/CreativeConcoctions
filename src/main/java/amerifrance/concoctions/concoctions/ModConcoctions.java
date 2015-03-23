package amerifrance.concoctions.concoctions;

import amerifrance.concoctions.api.Concoction;
import amerifrance.concoctions.api.ConcoctionsRegistry;

public class ModConcoctions {

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

    public static void registerConcoctions() {
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
    }
}
