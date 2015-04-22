package amerifrance.concoctions.util.event;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;

public class EventHandlers {

    public static void register() {
        MinecraftForge.EVENT_BUS.register(new ConcoctionHandler());
        FMLCommonHandler.instance().bus().register(new ConcoctionHandler());

        MinecraftForge.EVENT_BUS.register(new GeneralHandler.GuiHandler());
        FMLCommonHandler.instance().bus().register(new GeneralHandler.GuiHandler());

        MinecraftForge.EVENT_BUS.register(new GeneralHandler());
        FMLCommonHandler.instance().bus().register(new GeneralHandler());

        MinecraftForge.EVENT_BUS.register(new AnvilHandler());
        FMLCommonHandler.instance().bus().register(new AnvilHandler());

        MinecraftForge.EVENT_BUS.register(new EffectHandler());
        FMLCommonHandler.instance().bus().register(new EffectHandler());

        FMLCommonHandler.instance().bus().register(new KeyHandler());

        MinecraftForge.EVENT_BUS.register(new KnowledgeHandler());
        FMLCommonHandler.instance().bus().register(new KnowledgeHandler());
    }
}
