package amerifrance.concoctions.concoctions.basic;

import amerifrance.concoctions.api.Concoction;
import amerifrance.concoctions.api.ConcoctionType;
import net.minecraft.util.StatCollector;

import java.awt.*;

public class ConcoctionResistance extends Concoction {

    public ConcoctionResistance(){
        super("concoction.resistance", 20, Color.DARK_GRAY, ConcoctionType.GOOD);
    }
}
