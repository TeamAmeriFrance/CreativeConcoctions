package amerifrance.concoctions.concoctions.basic;

import amerifrance.concoctions.api.concoctions.Concoction;
import amerifrance.concoctions.api.concoctions.ConcoctionType;
import amerifrance.concoctions.api.concoctions.IConcoctionContext;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

import java.awt.*;

public class ConcoctionDamage extends Concoction {

    public DamageSource putMeDown = new DamageSource("PutMeDown");

    public ConcoctionDamage() {
        super("concoction.damage", 20, Color.BLACK, ConcoctionType.BAD);
    }

    @Override
    public void onEffectAdded(EntityLivingBase livingBase, IConcoctionContext ctx) {
        livingBase.attackEntityFrom(putMeDown, 1.0F * ctx.getConcoctionLevel());
    }

    @Override
    public boolean isConcoctionInstant() {
        return true;
    }
}
