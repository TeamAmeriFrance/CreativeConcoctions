package amerifrance.concoctions.concoctions.compound;

import amerifrance.concoctions.api.Concoction;
import amerifrance.concoctions.api.ConcoctionType;
import amerifrance.concoctions.api.ConcoctionsHelper;
import amerifrance.concoctions.api.IConcoctionContext;
import amerifrance.concoctions.concoctions.ModConcoctions;
import amerifrance.concoctions.concoctions.basic.ConcoctionPoison;
import amerifrance.concoctions.concoctions.basic.ConcoctionRegeneration;
import amerifrance.concoctions.guide.GuideConcoctions;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.StatCollector;

import java.awt.*;

public class ConcoctionVenomousVigor extends Concoction {

    public ConcoctionVenomousVigor() {
        super(StatCollector.translateToLocal("concoction.venomous.vigor"), 15, Color.BLACK, ConcoctionType.NEUTRAL, ModConcoctions.regeneration, ModConcoctions.poison);
    }

    @Override
    public void updateEffect(EntityLivingBase livingBase, IConcoctionContext ctx) {
        if (livingBase.getHealth() < livingBase.getMaxHealth() && ctx.getTicksLeft() % 20 == 0)
            livingBase.heal(1.0F * ctx.getConcoctionLevel());
    }

    @Override
    public void onEffectRemoved(EntityLivingBase livingBase, IConcoctionContext ctx) {
        ConcoctionsHelper.addConcoction(livingBase, ModConcoctions.poison, ctx.getConcoctionLevel(), ctx.getInitialDuration() / 2);
    }
}
