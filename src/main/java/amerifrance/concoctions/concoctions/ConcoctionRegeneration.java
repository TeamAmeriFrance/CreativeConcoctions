package amerifrance.concoctions.concoctions;

import amerifrance.concoctions.api.Concoction;
import amerifrance.concoctions.api.ConcoctionType;
import amerifrance.concoctions.api.IConcoctionContext;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.StatCollector;

import java.awt.*;

public class ConcoctionRegeneration extends Concoction {

    public ConcoctionRegeneration() {
        super(StatCollector.translateToLocal("concoction.regeneration"), 10, Color.RED, ConcoctionType.GOOD);
    }

    @Override
    public void updateEffect(EntityLivingBase livingBase, IConcoctionContext ctx) {
        if (livingBase.getHealth() < livingBase.getMaxHealth() && ctx.getTicksLeft() % 30 == 0)
            livingBase.heal(0.5F * ctx.getConcoctionLevel());
    }
}

