package amerifrance.concoctions.concoctions.basic;

import amerifrance.concoctions.api.Concoction;
import amerifrance.concoctions.api.ConcoctionType;
import net.minecraft.util.StatCollector;

import java.awt.*;

public class ConcoctionFeatherFall extends Concoction {

    public ConcoctionFeatherFall() {
        super(StatCollector.translateToLocal("concoction.feather.fall"), 30, Color.PINK, ConcoctionType.GOOD);
    }
}
