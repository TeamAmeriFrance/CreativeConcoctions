package amerifrance.concoctions.proxies;

import amerifrance.concoctions.guide.GuideConcoctions;
import cpw.mods.fml.client.FMLClientHandler;
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
}
