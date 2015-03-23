package amerifrance.concoctions.concoctions;

import amerifrance.concoctions.api.Concoction;
import amerifrance.concoctions.api.ConcoctionType;
import amerifrance.concoctions.api.IConcoctionContext;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.StatCollector;

import java.awt.*;

public class ConcoctionSwim extends Concoction {

    public ConcoctionSwim() {
        super(StatCollector.translateToLocal("concoction.swim"), 10, Color.BLUE, ConcoctionType.GOOD);
    }

    @Override
    public void updateEffect(EntityLivingBase livingBase, IConcoctionContext ctx) {
        if (livingBase.isInWater() && livingBase.moveForward > 0)
            livingBase.moveFlying(0.0F, 1.0F, 0.025F * ctx.getConcoctionLevel());
    }
}
