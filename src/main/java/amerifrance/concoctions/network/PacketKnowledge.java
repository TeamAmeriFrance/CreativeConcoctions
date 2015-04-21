package amerifrance.concoctions.network;

import amerifrance.concoctions.CreativeConcoctions;
import amerifrance.concoctions.api.ingredients.IngredientKnowledge;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class PacketKnowledge implements IMessage, IMessageHandler<PacketKnowledge, IMessage> {

    private int entityID;
    private NBTTagCompound tagCompound;

    public PacketKnowledge() {
    }

    public PacketKnowledge(EntityPlayer player, NBTTagCompound tagCompound) {
        this.entityID = player.getEntityId();
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
    public IMessage onMessage(PacketKnowledge message, MessageContext ctx) {
        World world = CreativeConcoctions.proxy.getClientWorld();
        EntityPlayer player = (EntityPlayer) world.getEntityByID(message.entityID);
        if (player != null) {
            IngredientKnowledge data = IngredientKnowledge.get(player);
            data.loadNBTData(message.tagCompound);
        }
        return null;
    }
}
