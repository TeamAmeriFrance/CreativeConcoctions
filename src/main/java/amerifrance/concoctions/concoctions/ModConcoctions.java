package amerifrance.concoctions.concoctions;

import amerifrance.concoctions.api.ConcoctionsRegistry;
import amerifrance.concoctions.objects.Concoction;

public class ModConcoctions {

    public static Concoction test;

    public static void registerConcoctions() {
        test = new TestConcoction();
        ConcoctionsRegistry.registerConcoction(test, "Concoction.Test");
    }
}
