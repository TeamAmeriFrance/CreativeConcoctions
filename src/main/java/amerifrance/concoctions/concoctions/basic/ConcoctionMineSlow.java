package amerifrance.concoctions.concoctions.basic;

import amerifrance.concoctions.api.concoctions.Concoction;
import amerifrance.concoctions.api.concoctions.ConcoctionType;

import java.awt.*;

public class ConcoctionMineSlow extends Concoction {

    public ConcoctionMineSlow() {
        super("concoction.mining.slow", 20, Color.DARK_GRAY, ConcoctionType.BAD);
    }
}
