package amerifrance.concoctions.concoctions;

import amerifrance.concoctions.api.Concoction;
import amerifrance.concoctions.api.ConcoctionType;
import net.minecraft.util.StatCollector;

import java.awt.*;

public class ConcoctionFireProtection extends Concoction {

    public ConcoctionFireProtection() {
        super(StatCollector.translateToLocal("concoction.fire.protection"), 3, Color.ORANGE, ConcoctionType.GOOD);
    }

    @Override
    public boolean shouldUpdate() {
        return false;
    }
}
