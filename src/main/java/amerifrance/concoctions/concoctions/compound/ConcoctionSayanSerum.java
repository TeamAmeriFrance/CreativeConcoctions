package amerifrance.concoctions.concoctions.compound;

import amerifrance.concoctions.api.Concoction;
import amerifrance.concoctions.api.ConcoctionType;
import amerifrance.concoctions.api.ConcoctionsHelper;
import amerifrance.concoctions.api.IConcoctionContext;
import amerifrance.concoctions.concoctions.ModConcoctions;
import net.minecraft.entity.EntityLivingBase;

import java.awt.*;

public class ConcoctionSayanSerum extends Concoction {

    public ConcoctionSayanSerum() {
        super("concoction.sayan.serum", 20, Color.YELLOW, ConcoctionType.NEUTRAL, ModConcoctions.strength, ModConcoctions.speed, ModConcoctions.resistance, ModConcoctions.regeneration, ModConcoctions.jumpBoost, ModConcoctions.slowness, ModConcoctions.mineSlow, ModConcoctions.hunger);
    }

    @Override
    public void onEffectAdded(EntityLivingBase livingBase, IConcoctionContext ctx) {
        for (Concoction c : getComponents()) {
            if (c.getConcotionType() == ConcoctionType.GOOD || c.getConcotionType() == ConcoctionType.NEUTRAL)
                c.onEffectAdded(livingBase, ctx);
        }
    }

    @Override
    public void updateEffect(EntityLivingBase livingBase, IConcoctionContext ctx) {
        for (Concoction c : getComponents()) {
            if (c.getConcotionType() == ConcoctionType.GOOD || c.getConcotionType() == ConcoctionType.NEUTRAL)
                c.updateEffect(livingBase, ctx);
        }
    }

    @Override
    public void onEffectRemoved(EntityLivingBase livingBase, IConcoctionContext ctx) {
        for (Concoction c : getComponents()) {
            if (c.getConcotionType() == ConcoctionType.GOOD || c.getConcotionType() == ConcoctionType.NEUTRAL)
                c.onEffectRemoved(livingBase, ctx);
            else ConcoctionsHelper.addConcoction(livingBase, c, ctx.getConcoctionLevel(), ctx.getInitialDuration());
        }
    }
}
