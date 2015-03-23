package amerifrance.concoctions.concoctions.basic;

import amerifrance.concoctions.api.Concoction;
import amerifrance.concoctions.api.ConcoctionType;
import net.minecraft.util.StatCollector;

import java.awt.*;

public class ConcoctionJumpBoost extends Concoction {

    public ConcoctionJumpBoost() {
        super(StatCollector.translateToLocal("concoction.jump.boost"), 30, Color.BLUE, ConcoctionType.GOOD);
    }
}
