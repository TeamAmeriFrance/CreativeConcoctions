package amerifrance.concoctions.concoctions.basic;

import amerifrance.concoctions.api.Concoction;
import amerifrance.concoctions.api.IConcoctionContext;
import amerifrance.concoctions.util.RayTraceHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.util.StatCollector;

import java.awt.*;

public class ConcoctionFireball extends Concoction {

    public ConcoctionFireball() {
        super("concoction.fireball", 2, Color.MAGENTA);
    }

    @Override
    public void updateEffect(EntityLivingBase livingBase, IConcoctionContext ctx) {
        if (!(livingBase instanceof EntityPlayer)) {
            Entity target = RayTraceHelper.rayTrace(livingBase, 16, 0);
            if (target != null) {
                double dX = target.posX - livingBase.posX;
                double dY = target.posY - livingBase.posY;
                double dZ = target.posZ - livingBase.posZ;

                if (ctx.getConcoctionLevel() == 1) {
                    EntitySmallFireball fireball = new EntitySmallFireball(livingBase.worldObj, livingBase, dX, dY, dZ);
                    fireball.posX += 1 * dX / Math.abs(dX);
                    fireball.posY += livingBase.height / 2;
                    fireball.posZ += 1 * dZ / Math.abs(dZ);
                    livingBase.worldObj.spawnEntityInWorld(fireball);
                } else if (ctx.getConcoctionLevel() == 2) {
                    EntityLargeFireball fireball = new EntityLargeFireball(livingBase.worldObj, livingBase, dX, dY, dZ);
                    fireball.posX += 1 * dX / Math.abs(dX);
                    fireball.posY += livingBase.height / 2;
                    fireball.posZ += 1 * dZ / Math.abs(dZ);
                    livingBase.worldObj.spawnEntityInWorld(fireball);
                }
            }
        }
    }
}
