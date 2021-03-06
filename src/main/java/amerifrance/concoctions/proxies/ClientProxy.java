package amerifrance.concoctions.proxies;

import amerifrance.concoctions.blocks.BlockCauldronBase;
import amerifrance.concoctions.client.render.RenderCauldron;
import amerifrance.concoctions.entities.EntityCoatedArrow;
import amerifrance.concoctions.entities.EntityConcoction;
import amerifrance.concoctions.registry.ItemsRegistry;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy {

    @Override
    public World getClientWorld() {
        return FMLClientHandler.instance().getClient().theWorld;
    }

    @Override
    public void registerRenders() {
        RenderingRegistry.registerBlockHandler(BlockCauldronBase.renderID, new RenderCauldron());

        RenderingRegistry.registerEntityRenderingHandler(EntityConcoction.class, new RenderSnowball(ItemsRegistry.concoctionThrowable));
        RenderingRegistry.registerEntityRenderingHandler(EntityCoatedArrow.class, new RenderArrow());
    }

    @Override
    public void cauldronSplash(World world, double x, double y, double z, int stacksize) {
        double dx = x + 0.5;
        double dy = y + 0.5;
        double dz = z + 0.5;
        for (float j = 0; j <= 1; j += 0.1) {
            EntityFX particle = new EntitySplashFX(world, dx, dy, dz, 0, 0, 0);
            particle.multipleParticleScaleBy(1 + 0.1F * stacksize);
            FMLClientHandler.instance().getClient().effectRenderer.addEffect(particle);
        }
        world.playSound(x + 0.5, y + 0.5, z + 0.5, "game.neutral.swim.splash", world.rand.nextFloat() * 0.25F + 0.75F, world.rand.nextFloat() * 1.0F + 0.5F, false);
    }

    @Override
    public void cauldronFumes(World world, double x, double y, double z) {
        EntityFX particle = new EntitySmokeFX(world, x + 0.5, y, z + 0.5, 0, 0, 0);
        FMLClientHandler.instance().getClient().effectRenderer.addEffect(particle);
    }

    @Override
    public void poisonousFume(World world, double x, double y, double z) {
        EntityFX particle = new EntitySmokeFX(world, x + 0.5, y, z + 0.5, 0, 0, 0);
        particle.setRBGColorF(0, (float) 150 / 255, 0);
        FMLClientHandler.instance().getClient().effectRenderer.addEffect(particle);
    }

    @Override
    public void cauldronMelt(World world, double x, double y, double z, int type) {
        EntityFX particle;
        if (type == 0) {
            particle = new EntityExplodeFX(world, x + 0.5, y, z + 0.5, 0, 0, 0);
            world.playSound(x + 0.5, y + 0.5, z + 0.5, "game.tnt.primed", world.rand.nextFloat() * 0.25F + 0.75F, world.rand.nextFloat() * 1.0F + 0.5F, false);
        } else {
            particle = new EntityHugeExplodeFX(world, x + 0.5, y, z + 0.5, 0, 0, 0);
            world.playSound(x + 0.5, y + 0.5, z + 0.5, "random.explode", world.rand.nextFloat() * 0.25F + 0.75F, world.rand.nextFloat() * 1.0F + 0.5F, false);
        }
        FMLClientHandler.instance().getClient().effectRenderer.addEffect(particle);
    }
}
