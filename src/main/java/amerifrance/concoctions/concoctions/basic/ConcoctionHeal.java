package amerifrance.concoctions.concoctions.basic;

import amerifrance.concoctions.api.concoctions.Concoction;
import amerifrance.concoctions.api.concoctions.ConcoctionType;
import amerifrance.concoctions.api.concoctions.IConcoctionContext;
import net.minecraft.entity.EntityLivingBase;

import java.awt.*;

public class ConcoctionHeal extends Concoction {

    public ConcoctionHeal() {
        super("concoction.heal", 20, Color.PINK, ConcoctionType.GOOD);
    }

    @Override
    public void onEffectAdded(EntityLivingBase livingBase, IConcoctionContext ctx) {
        livingBase.heal(1.0F * ctx.getConcoctionLevel());
    }

    @Override
    public boolean isConcoctionInstant() {
        return true;
    }
}
