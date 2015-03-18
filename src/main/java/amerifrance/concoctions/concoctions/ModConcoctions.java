package amerifrance.concoctions.concoctions;

import amerifrance.concoctions.api.ConcoctionsRegistry;
import amerifrance.concoctions.objects.Concoction;

public class ModConcoctions {

    public static Concoction speed;

    public static void registerConcoctions() {
        speed = new ConcoctionSpeed();
        ConcoctionsRegistry.registerConcoction(speed, "ConcoctionSpeed");
    }
}
