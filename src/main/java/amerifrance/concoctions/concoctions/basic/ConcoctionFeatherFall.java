package amerifrance.concoctions.concoctions.basic;

import amerifrance.concoctions.api.concoctions.Concoction;
import amerifrance.concoctions.api.concoctions.ConcoctionType;

import java.awt.*;

public class ConcoctionFeatherFall extends Concoction {

    public ConcoctionFeatherFall() {
        super("concoction.feather.fall", 30, Color.PINK, ConcoctionType.GOOD);
    }
}
