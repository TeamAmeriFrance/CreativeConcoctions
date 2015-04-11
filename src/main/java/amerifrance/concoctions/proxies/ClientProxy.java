package amerifrance.concoctions.proxies;

import amerifrance.concoctions.blocks.BlockCauldronBase;
import amerifrance.concoctions.client.render.RenderCauldron;
import amerifrance.concoctions.guide.GuideConcoctions;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy {

    @Override
    public World getClientWorld() {
        return FMLClientHandler.instance().getClient().theWorld;
    }

    @Override
    public void setConcoctionBook() {
        GuideConcoctions.setConcoctionBook();
    }

    @Override
    public void registerRenders() {
        RenderingRegistry.registerBlockHandler(BlockCauldronBase.renderID, new RenderCauldron());
    }

    @Override
    public void cauldronSplash(World world, double x, double y, double z) {
        world.playSound(x + 0.5, y + 0.5, z + 0.5, "game.neutral.swim.splash", world.rand.nextFloat() * 0.25F + 0.75F, world.rand.nextFloat() * 1.0F + 0.5F, false);
    }
}
