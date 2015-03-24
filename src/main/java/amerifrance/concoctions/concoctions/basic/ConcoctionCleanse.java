package amerifrance.concoctions.concoctions.basic;

import amerifrance.concoctions.api.Concoction;
import amerifrance.concoctions.api.ConcoctionType;
import amerifrance.concoctions.api.IConcoctionContext;
import amerifrance.concoctions.util.LivingConcoctions;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.StatCollector;

import java.awt.*;
import java.util.Iterator;

public class ConcoctionCleanse extends Concoction {
    public ConcoctionCleanse() {
        super("concoction.cleanse", 20, Color.WHITE, ConcoctionType.NEUTRAL);
    }

    @Override
    public void onEffectAdded(EntityLivingBase livingBase, IConcoctionContext ctx) {
        if (LivingConcoctions.get(livingBase) != null) {
            if (LivingConcoctions.getActiveConcotions(livingBase) != null && !LivingConcoctions.getActiveConcotions(livingBase).isEmpty()) {
                Iterator<IConcoctionContext> iterator = LivingConcoctions.getActiveConcotions(livingBase).iterator();
                while (iterator.hasNext()) {
                    IConcoctionContext otherCtx = iterator.next();
                    if (otherCtx.getConcoction().getConcotionType() == ConcoctionType.GOOD) iterator.remove();
                }
            }
        }
    }
}
