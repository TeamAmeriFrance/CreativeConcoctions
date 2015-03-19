package amerifrance.concoctions.concoctions;

import amerifrance.concoctions.api.Concoction;
import amerifrance.concoctions.api.ConcoctionsRegistry;

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
