package amerifrance.concoctions.util.event;

import amerifrance.concoctions.api.CreativeConcoctionsAPI;
import amerifrance.concoctions.registry.ItemsRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;

import java.util.Random;

public class LivingDropsHandler {

    Random random;

    public LivingDropsHandler() {
        random = new Random();
    }

    @SubscribeEvent
    public void onAnimalDrop(LivingDropsEvent event) {
        EntityLivingBase livingBase = event.entityLiving;
        if (livingBase instanceof EntityAnimal) {
            EntityAnimal animal = (EntityAnimal) livingBase;
            if (random.nextInt(3) == 0) {
                event.drops.add(CreativeConcoctionsAPI.createEntityItem(animal.worldObj, animal.posX, animal.posY, animal.posZ, new ItemStack(ItemsRegistry.ingredients, 1, 13)));
            }
        }
    }

    @SubscribeEvent
    public void onSpiderDrop(LivingDropsEvent event) {
        EntityLivingBase livingBase = event.entityLiving;
        if (livingBase instanceof EntitySpider) {
            EntitySpider spider = (EntitySpider) livingBase;
            if (random.nextInt(5) == 0) {
                event.drops.add(CreativeConcoctionsAPI.createEntityItem(spider.worldObj, spider.posX, spider.posY, spider.posZ, new ItemStack(ItemsRegistry.ingredients, 1, 20)));
            }
            if (random.nextInt(3) == 0) {
                event.drops.add(CreativeConcoctionsAPI.createEntityItem(spider.worldObj, spider.posX, spider.posY, spider.posZ, new ItemStack(ItemsRegistry.ingredients, 1, 26)));
            }
        }
    }

    @SubscribeEvent
    public void onZombieDrop(LivingDropsEvent event) {
        EntityLivingBase livingBase = event.entityLiving;
        if (livingBase instanceof EntityZombie) {
            EntityZombie zombie = (EntityZombie) livingBase;
            if (random.nextInt(5) == 0) {
                event.drops.add(CreativeConcoctionsAPI.createEntityItem(zombie.worldObj, zombie.posX, zombie.posY, zombie.posZ, new ItemStack(ItemsRegistry.ingredients, 1, 28)));
            }
            if (random.nextInt(4) == 0) {
                event.drops.add(CreativeConcoctionsAPI.createEntityItem(zombie.worldObj, zombie.posX, zombie.posY, zombie.posZ, new ItemStack(ItemsRegistry.ingredients, 1, 27)));
            }
        }
    }

    @SubscribeEvent
    public void onPlayerDrop(PlayerDropsEvent event) {
        EntityPlayer player = event.entityPlayer;
        if (random.nextInt(5) == 0) {
            event.drops.add(CreativeConcoctionsAPI.createEntityItem(player.worldObj, player.posX, player.posY, player.posZ, new ItemStack(ItemsRegistry.ingredients, 1, 23)));
        }
        if (random.nextInt(4) == 0) {
            event.drops.add(CreativeConcoctionsAPI.createEntityItem(player.worldObj, player.posX, player.posY, player.posZ, new ItemStack(ItemsRegistry.ingredients, 1, 22)));
        }
        if (random.nextInt(3) == 0) {
            event.drops.add(CreativeConcoctionsAPI.createEntityItem(player.worldObj, player.posX, player.posY, player.posZ, new ItemStack(ItemsRegistry.ingredients, 1, 21)));
        }
    }
}
