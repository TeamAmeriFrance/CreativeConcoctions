package amerifrance.concoctions.concoctions;

import amerifrance.concoctions.api.ConcoctionsRegistry;
import amerifrance.concoctions.api.Concoction;

public class ModConcoctions {

    public static Concoction speed;
    public static Concoction hellEyes;

    public static void registerConcoctions() {
        speed = new ConcoctionSpeed();
        ConcoctionsRegistry.registerConcoction(speed, "ConcoctionSpeed");

        hellEyes = new ConcoctionHellEyes();
        ConcoctionsRegistry.registerConcoction(hellEyes, "HellEyes");
    }
}
