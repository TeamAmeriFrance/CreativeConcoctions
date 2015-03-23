package amerifrance.concoctions.concoctions.basic;

import amerifrance.concoctions.api.Concoction;
import amerifrance.concoctions.api.ConcoctionType;
import net.minecraft.util.StatCollector;

import java.awt.*;

public class ConcoctionMineSlow extends Concoction {

    public ConcoctionMineSlow() {
        super(StatCollector.translateToLocal("concoction.mining.slow"), 20, Color.DARK_GRAY, ConcoctionType.BAD);
    }
}
