package amerifrance.concoctions.concoctions.basic;

import amerifrance.concoctions.api.concoctions.Concoction;
import amerifrance.concoctions.api.concoctions.ConcoctionType;

import java.awt.*;

public class ConcoctionJumpBoost extends Concoction {

    public ConcoctionJumpBoost() {
        super("concoction.jump.boost", 30, Color.BLUE, ConcoctionType.GOOD);
    }
}
