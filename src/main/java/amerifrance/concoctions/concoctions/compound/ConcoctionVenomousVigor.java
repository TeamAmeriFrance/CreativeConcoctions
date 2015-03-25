package amerifrance.concoctions.concoctions.compound;

import amerifrance.concoctions.api.concoctions.Concoction;
import amerifrance.concoctions.api.concoctions.ConcoctionType;
import amerifrance.concoctions.api.concoctions.ConcoctionsHelper;
import amerifrance.concoctions.api.concoctions.IConcoctionContext;
import amerifrance.concoctions.concoctions.ModConcoctions;
import net.minecraft.entity.EntityLivingBase;

import java.awt.*;

public class ConcoctionVenomousVigor extends Concoction {

    public ConcoctionVenomousVigor() {
        super("concoction.venomous.vigor", 15, Color.BLACK, ConcoctionType.NEUTRAL, ModConcoctions.regeneration, ModConcoctions.poison);
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
