package amerifrance.concoctions.concoctions.basic;

import amerifrance.concoctions.api.Concoction;
import amerifrance.concoctions.api.ConcoctionType;
import amerifrance.concoctions.api.IConcoctionContext;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;

import java.awt.*;

public class ConcoctionPoison extends Concoction {

    public ConcoctionPoison() {
        super(StatCollector.translateToLocal("concoction.poison"), 10, Color.GREEN, ConcoctionType.BAD);
    }

    @Override
    public void updateEffect(EntityLivingBase livingBase, IConcoctionContext ctx) {
        if (ctx.getTicksLeft() % 20 == 0 && livingBase.getHealth() > 1.0F)
            livingBase.attackEntityFrom(DamageSource.magic, 1.0F * ctx.getConcoctionLevel());
    }
}
