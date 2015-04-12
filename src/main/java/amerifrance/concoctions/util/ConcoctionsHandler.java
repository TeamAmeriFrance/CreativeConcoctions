package amerifrance.concoctions.util;


import amerifrance.concoctions.api.concoctions.IConcoctionContext;
import amerifrance.concoctions.api.concoctions.LivingConcoctions;
import amerifrance.concoctions.network.PacketConcoctions;
import amerifrance.concoctions.network.PacketHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

import java.util.Iterator;
import java.util.LinkedList;

public class ConcoctionsHandler {

    @SubscribeEvent
    public void onCreateEntity(EntityEvent.EntityConstructing event) {
        if (event.entity instanceof EntityLivingBase && LivingConcoctions.get((EntityLivingBase) event.entity) == null) {
            LivingConcoctions.create((EntityLivingBase) event.entity);
        }
    }

    @SubscribeEvent
    public void onLivingTick(LivingEvent.LivingUpdateEvent event) {
        EntityLivingBase livingBase = event.entityLiving;
        if (LivingConcoctions.get(livingBase) != null) {
            if (LivingConcoctions.getActiveConcotions(livingBase) != null && !LivingConcoctions.getActiveConcotions(livingBase).isEmpty()) {
                LinkedList<IConcoctionContext> toRemove = new LinkedList<IConcoctionContext>();
                Iterator<IConcoctionContext> iterator = LivingConcoctions.getActiveConcotions(livingBase).iterator();
                while (iterator.hasNext()) {
                    IConcoctionContext ctx = iterator.next();
                    if (ctx.getTicksLeft() > 0) {
                        ctx.onUpdate(livingBase);
                    } else {
                        toRemove.add(ctx);
                        iterator.remove();
                    }
                }
                for (IConcoctionContext ctx : toRemove) ctx.onRemoved(livingBase);
            }
        }
    }

    @SubscribeEvent
    public void onPlayerStartTracking(PlayerEvent.StartTracking event) {
        if (event.target instanceof EntityLivingBase) {
            syncConcoctions((EntityLivingBase) event.target, (EntityPlayerMP) event.entityPlayer);
        }
    }

    @SubscribeEvent
    public void onPlayerLogin(cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event) {
        syncConcoctions(event.player, (EntityPlayerMP) event.player);
    }

    @SubscribeEvent
    public void onPlayerChangeDimension(cpw.mods.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent event) {
        syncConcoctions(event.player, (EntityPlayerMP) event.player);
    }

    @SubscribeEvent
    public void onPlayerSpawn(cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent event) {
        syncConcoctions(event.player, (EntityPlayerMP) event.player);
    }

    public static void syncConcoctions(EntityLivingBase livingBase, EntityPlayerMP player) {
        LivingConcoctions data = LivingConcoctions.get(livingBase);
        if (!data.getActiveConcoctions().isEmpty()) {
            NBTTagCompound tagCompound = new NBTTagCompound();
            data.saveNBTData(tagCompound);
            PacketHandler.INSTANCE.sendTo(new PacketConcoctions(livingBase, tagCompound), player);
        }
    }
}
