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
}
