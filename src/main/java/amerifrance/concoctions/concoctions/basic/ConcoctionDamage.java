package amerifrance.concoctions.concoctions.basic;

import amerifrance.concoctions.api.Concoction;
import amerifrance.concoctions.api.ConcoctionType;
import amerifrance.concoctions.api.IConcoctionContext;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;

import java.awt.*;

public class ConcoctionDamage extends Concoction {

    public DamageSource putMeDown = new DamageSource("PutMeDown").setDifficultyScaled();

    public ConcoctionDamage() {
        super("concoction.damage", 20, Color.BLACK, ConcoctionType.BAD);
    }

    @Override
    public void onEffectAdded(EntityLivingBase livingBase, IConcoctionContext ctx) {
        livingBase.attackEntityFrom(putMeDown, 1.0F * ctx.getConcoctionLevel());
    }
}
