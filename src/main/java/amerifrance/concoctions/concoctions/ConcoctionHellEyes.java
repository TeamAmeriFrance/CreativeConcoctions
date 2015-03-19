package amerifrance.concoctions.concoctions;

import amerifrance.concoctions.api.Concoction;
import amerifrance.concoctions.api.IConcoctionContext;
import amerifrance.concoctions.util.RayTraceHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;

import java.awt.*;

public class ConcoctionHellEyes extends Concoction {

    public DamageSource hellEyes = new DamageSource("HellEyes").setDamageBypassesArmor();

    public ConcoctionHellEyes() {
        super(StatCollector.translateToLocal("concoction.hell.eyes"), 10, Color.BLACK);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void updateEffect(EntityLivingBase livingBase, IConcoctionContext ctx) {
        Entity entity = RayTraceHelper.rayTrace(livingBase, 16, 0);
        if (entity instanceof EntityLivingBase) {
            entity.attackEntityFrom(hellEyes, 1.0F * ctx.getConcoctionLevel());
        }
    }
}
