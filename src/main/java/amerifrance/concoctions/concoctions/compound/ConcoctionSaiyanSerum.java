package amerifrance.concoctions.concoctions.compound;

import amerifrance.concoctions.api.concoctions.Concoction;
import amerifrance.concoctions.api.concoctions.ConcoctionType;
import amerifrance.concoctions.api.concoctions.ConcoctionsHelper;
import amerifrance.concoctions.api.concoctions.IConcoctionContext;
import amerifrance.concoctions.registry.ModConcoctions;
import net.minecraft.entity.EntityLivingBase;

import java.awt.*;

public class ConcoctionSaiyanSerum extends Concoction {

    public ConcoctionSaiyanSerum() {
        super("concoction.saiyan.serum", 20, Color.YELLOW, ConcoctionType.NEUTRAL, ModConcoctions.strength, ModConcoctions.speed, ModConcoctions.resistance, ModConcoctions.regeneration, ModConcoctions.jumpBoost, ModConcoctions.slowness, ModConcoctions.mineSlow, ModConcoctions.hunger);
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
