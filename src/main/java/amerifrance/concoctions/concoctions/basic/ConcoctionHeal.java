package amerifrance.concoctions.concoctions.basic;

import amerifrance.concoctions.api.Concoction;
import amerifrance.concoctions.api.ConcoctionType;
import amerifrance.concoctions.api.IConcoctionContext;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.StatCollector;

import java.awt.*;

public class ConcoctionHeal extends Concoction {

    public ConcoctionHeal() {
        super("concoction.heal", 20, Color.PINK, ConcoctionType.GOOD);
    }

    @Override
    public void onEffectAdded(EntityLivingBase livingBase, IConcoctionContext ctx) {
        livingBase.heal(1.0F * ctx.getConcoctionLevel());
    }
}
