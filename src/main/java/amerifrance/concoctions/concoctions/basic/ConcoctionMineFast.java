package amerifrance.concoctions.concoctions.basic;

import amerifrance.concoctions.api.Concoction;
import amerifrance.concoctions.api.ConcoctionType;
import net.minecraft.util.StatCollector;

import java.awt.*;

public class ConcoctionMineFast extends Concoction {

    public ConcoctionMineFast() {
        super("concoction.mining.fast", 20, Color.RED, ConcoctionType.GOOD);
    }
}