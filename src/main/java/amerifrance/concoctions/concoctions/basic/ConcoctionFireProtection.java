package amerifrance.concoctions.concoctions.basic;

import amerifrance.concoctions.api.concoctions.Concoction;
import amerifrance.concoctions.api.concoctions.ConcoctionType;

import java.awt.*;

public class ConcoctionFireProtection extends Concoction {

    public ConcoctionFireProtection() {
        super("concoction.fire.protection", 3, Color.ORANGE, ConcoctionType.GOOD);
    }
}
