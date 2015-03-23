package amerifrance.concoctions.concoctions;

import amerifrance.concoctions.api.Concoction;
import amerifrance.concoctions.api.ConcoctionType;
import net.minecraft.util.StatCollector;

import java.awt.*;

public class ConcoctionResistance extends Concoction {

    public ConcoctionResistance(){
        super(StatCollector.translateToLocal("concoction.resistance"), 20, Color.DARK_GRAY, ConcoctionType.GOOD);
    }
}