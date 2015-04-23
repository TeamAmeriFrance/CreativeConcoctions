package amerifrance.concoctions.util.event;

import amerifrance.concoctions.api.concoctions.LivingConcoctions;
import amerifrance.concoctions.api.ingredients.IngredientKnowledge;
import amerifrance.concoctions.network.PacketHandler;
import amerifrance.concoctions.network.PacketKnowledge;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityEvent;

public class KnowledgeHandler {

    public static void syncKnowledge(EntityPlayer player) {
        LivingConcoctions data = LivingConcoctions.get(player);
        if (!data.getActiveConcoctions().isEmpty()) {
            NBTTagCompound tagCompound = new NBTTagCompound();
            data.saveNBTData(tagCompound);
            PacketHandler.INSTANCE.sendTo(new PacketKnowledge(player, tagCompound), (EntityPlayerMP) player);
        }
    }

    @SubscribeEvent
    public void onCreateEntity(EntityEvent.EntityConstructing event) {
        if (event.entity instanceof EntityPlayer && IngredientKnowledge.get((EntityPlayer) event.entity) == null) {
            IngredientKnowledge.create((EntityPlayer) event.entity);
        }
    }

    @SubscribeEvent
    public void onPlayerChangeDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        syncKnowledge(event.player);
    }

    @SubscribeEvent
    public void onPlayerSpawn(PlayerEvent.PlayerRespawnEvent event) {
        syncKnowledge(event.player);
    }

    @SubscribeEvent
    public void onPlayerClone(net.minecraftforge.event.entity.player.PlayerEvent.Clone event) {
        NBTTagCompound tagCompound = new NBTTagCompound();
        IngredientKnowledge.get(event.original).saveNBTData(tagCompound);
        IngredientKnowledge.get(event.entityPlayer).saveNBTData(tagCompound);
        syncKnowledge(event.entityPlayer);
    }
}
