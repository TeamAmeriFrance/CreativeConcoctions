package amerifrance.concoctions.concoctions.basic;

import amerifrance.concoctions.api.concoctions.Concoction;
import amerifrance.concoctions.api.concoctions.ConcoctionType;
import amerifrance.concoctions.api.concoctions.IConcoctionContext;
import net.minecraft.entity.EntityLivingBase;

import java.awt.*;

public class ConcoctionRegeneration extends Concoction {

    public ConcoctionRegeneration() {
        super("concoction.regeneration", 10, Color.RED, ConcoctionType.GOOD);
    }

    @Override
    public void updateEffect(EntityLivingBase livingBase, IConcoctionContext ctx) {
        if (livingBase.getHealth() < livingBase.getMaxHealth() && ctx.getTicksLeft() % 30 == 0)
            livingBase.heal(0.5F * ctx.getConcoctionLevel());
    }
}

