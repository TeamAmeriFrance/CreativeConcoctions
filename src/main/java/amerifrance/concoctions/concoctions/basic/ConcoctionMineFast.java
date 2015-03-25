package amerifrance.concoctions.concoctions.basic;

import amerifrance.concoctions.api.concoctions.Concoction;
import amerifrance.concoctions.api.concoctions.ConcoctionType;

import java.awt.*;

public class ConcoctionMineFast extends Concoction {

    public ConcoctionMineFast() {
        super("concoction.mining.fast", 20, Color.RED, ConcoctionType.GOOD);
    }
}