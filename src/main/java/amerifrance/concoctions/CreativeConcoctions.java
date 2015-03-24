package amerifrance.concoctions;

import amerifrance.concoctions.concoctions.ModConcoctions;
import amerifrance.concoctions.items.ItemsRegistry;
import amerifrance.concoctions.network.PacketHandler;
import amerifrance.concoctions.proxies.CommonProxy;
import amerifrance.concoctions.util.ClientEventHandler;
import amerifrance.concoctions.util.ConcoctionsHandler;
import amerifrance.concoctions.util.EventHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

import java.io.File;

@Mod(modid = ModInformation.ID, name = ModInformation.NAME, version = ModInformation.VERSION, dependencies = ModInformation.DEPEND)
public class CreativeConcoctions {

    public static CreativeTabs tabConcoction = new CreativeTabs(ModInformation.ID + ".creativeTab") {
        @Override
        public ItemStack getIconItemStack() {
            return new ItemStack(Items.golden_apple);
        }

        @Override
        public Item getTabIconItem() {
            return new Item();
        }
    };

    @Mod.Instance
    public static CreativeConcoctions instance;
    @SidedProxy(clientSide = ModInformation.CLIENTPROXY, serverSide = ModInformation.COMMONPROXY)
    public static CommonProxy proxy;

    private static File configDir;

    public static File getConfigDir() {
        return configDir;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        instance = this;
        configDir = new File(event.getModConfigurationDirectory() + "/" + ModInformation.NAME);
        ModConcoctions.registerBasicConcoctions();
        ModConcoctions.registerCompoundConcoctions();
        ItemsRegistry.registerItems();
        proxy.setConcoctionBook();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new ConcoctionsHandler());
        FMLCommonHandler.instance().bus().register(new ConcoctionsHandler());
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        FMLCommonHandler.instance().bus().register(new EventHandler());
        FMLCommonHandler.instance().bus().register(new ClientEventHandler());
        PacketHandler.registerPackets();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }
}
