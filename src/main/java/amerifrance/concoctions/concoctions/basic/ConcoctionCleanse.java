package amerifrance.concoctions.concoctions.basic;

import amerifrance.concoctions.api.concoctions.Concoction;
import amerifrance.concoctions.api.concoctions.ConcoctionType;
import amerifrance.concoctions.api.concoctions.ConcoctionsHelper;
import amerifrance.concoctions.api.concoctions.IConcoctionContext;
import net.minecraft.entity.EntityLivingBase;

import java.awt.*;

public class ConcoctionCleanse extends Concoction {
    public ConcoctionCleanse() {
        super("concoction.cleanse", 20, Color.WHITE, ConcoctionType.NEUTRAL);
    }

    @Override
    public void onEffectAdded(EntityLivingBase livingBase, IConcoctionContext ctx) {
        ConcoctionsHelper.clearActiveConcoctions(livingBase);
    }

    @Override
    public boolean isConcoctionInstant() {
        return true;
    }
}
