package amerifrance.concoctions.network;

import amerifrance.concoctions.api.ConcoctionsHelper;
import amerifrance.concoctions.api.IConcoctionContext;
import amerifrance.concoctions.concoctions.ModConcoctions;
import amerifrance.concoctions.util.RayTraceHelper;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;

public class PacketFireBall implements IMessage, IMessageHandler<PacketFireBall, IMessage> {//Yes, PUUN too

    public PacketFireBall() {
    }

    @Override
    public void fromBytes(ByteBuf buf) {
    }

    @Override
    public void toBytes(ByteBuf buf) {
    }

    @Override
    public IMessage onMessage(PacketFireBall message, MessageContext ctx) {
        EntityPlayer player = ctx.getServerHandler().playerEntity;
        IConcoctionContext concoctionContext = ConcoctionsHelper.getActiveConcoction(player, ModConcoctions.fireball);
        if (concoctionContext != null) {
            Entity target = RayTraceHelper.rayTrace(player, 16, 0);
            if (target != null) {
                double dX = target.posX - player.posX;
                double dY = target.posY - player.posY;
                double dZ = target.posZ - player.posZ;

                if (concoctionContext.getConcoctionLevel() == 1) {
                    EntitySmallFireball fireball = new EntitySmallFireball(player.worldObj, player, dX, dY, dZ);
                    fireball.posX += 1 * dX / Math.abs(dX);
                    fireball.posY += player.height / 2;
                    fireball.posZ += 1 * dZ / Math.abs(dZ);
                    player.worldObj.spawnEntityInWorld(fireball);
                } else if (concoctionContext.getConcoctionLevel() == 2) {
                    EntityLargeFireball fireball = new EntityLargeFireball(player.worldObj, player, dX, dY, dZ);
                    fireball.posX += 1 * dX / Math.abs(dX);
                    fireball.posY += player.height / 2;
                    fireball.posZ += 1 * dZ / Math.abs(dZ);
                    player.worldObj.spawnEntityInWorld(fireball);
                }
            }
        }
        return null;
    }
}
