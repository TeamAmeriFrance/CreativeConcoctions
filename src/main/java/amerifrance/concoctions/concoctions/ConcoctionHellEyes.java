package amerifrance.concoctions.concoctions;

import amerifrance.concoctions.api.Concoction;
import amerifrance.concoctions.objects.ConcoctionContext;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;

import java.awt.*;
import java.util.Iterator;

public class ConcoctionHellEyes extends Concoction {

    public DamageSource hellEyes = new DamageSource("HellEyes").setDamageBypassesArmor().setMagicDamage();

    public ConcoctionHellEyes() {
        super(StatCollector.translateToLocal("concoction.hell.eyes"), 10, Color.BLACK);
    }

    @Override
    public void updateEffect(EntityLivingBase livingBase, ConcoctionContext ctx) {
        AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(livingBase.posX - 10, livingBase.posY - 10, livingBase.posZ - 10, livingBase.posX + 10, livingBase.posY + 10, livingBase.posZ + 10);
        Iterator<EntityLivingBase> iter = livingBase.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, bb).iterator();
        while (iter.hasNext()) {
            EntityLivingBase victim = iter.next();
            if (shouldAttackEntity(livingBase, victim) && victim != livingBase) {
                livingBase.attackEntityFrom(hellEyes, 1.0F * ctx.getConcoctionLevel());
            }
        }
    }

    private boolean shouldAttackEntity(EntityLivingBase attacker, EntityLivingBase victim) {
        Vec3 vec3 = attacker.getLook(1.0F).normalize();
        Vec3 vec31 = Vec3.createVectorHelper(victim.posX - attacker.posX, victim.boundingBox.minY + (double) (victim.height / 2.0F) - (attacker.posY + (double) attacker.getEyeHeight()), victim.posZ - attacker.posZ);
        double d0 = vec31.lengthVector();
        vec31 = vec31.normalize();
        double d1 = vec3.dotProduct(vec31);
        return d1 > 1.0D - 0.025D / d0 && attacker.canEntityBeSeen(victim);
    }
}
