package amerifrance.concoctions.util.event;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;

public class EventHandlers {

    public static void register() {
        MinecraftForge.EVENT_BUS.register(new ConcoctionsHandler());
        FMLCommonHandler.instance().bus().register(new ConcoctionsHandler());

        MinecraftForge.EVENT_BUS.register(new EventHandler());
        FMLCommonHandler.instance().bus().register(new EventHandler());

        FMLCommonHandler.instance().bus().register(new ClientEventHandler());

        MinecraftForge.EVENT_BUS.register(new KnowledgeHandler());
        FMLCommonHandler.instance().bus().register(new KnowledgeHandler());
    }
}
