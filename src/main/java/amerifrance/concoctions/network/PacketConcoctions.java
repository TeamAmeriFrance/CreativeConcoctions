package amerifrance.concoctions.network;

import amerifrance.concoctions.CreativeConcoctions;
import amerifrance.concoctions.api.concoctions.LivingConcoctions;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class PacketConcoctions implements IMessage, IMessageHandler<PacketConcoctions, IMessage> {

    private int entityID;
    private NBTTagCompound tagCompound;

    public PacketConcoctions() {
    }

    public PacketConcoctions(EntityLivingBase livingBase, NBTTagCompound tagCompound) {
        this.entityID = livingBase.getEntityId();
        this.tagCompound = tagCompound;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.entityID = buf.readInt();
        this.tagCompound = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(entityID);
        ByteBufUtils.writeTag(buf, tagCompound);
    }

    @Override
    public IMessage onMessage(PacketConcoctions message, MessageContext ctx) {
        World world = CreativeConcoctions.proxy.getClientWorld();
        EntityLivingBase livingBase = (EntityLivingBase) world.getEntityByID(message.entityID);
        if (livingBase != null) {
            LivingConcoctions data = LivingConcoctions.get(livingBase);
            data.loadNBTData(message.tagCompound);
        }
        return null;
    }
}
