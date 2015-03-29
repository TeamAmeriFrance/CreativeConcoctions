package amerifrance.concoctions.concoctions.basic;

import amerifrance.concoctions.api.concoctions.Concoction;
import amerifrance.concoctions.api.concoctions.ConcoctionType;

import java.awt.*;

public class ConcoctionResistance extends Concoction {

    public ConcoctionResistance() {
        super("concoction.resistance", 20, Color.DARK_GRAY, ConcoctionType.GOOD);
    }
}
