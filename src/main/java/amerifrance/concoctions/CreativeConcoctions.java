package amerifrance.concoctions;

import amerifrance.concoctions.guide.GuideConcoctions;
import amerifrance.concoctions.network.PacketHandler;
import amerifrance.concoctions.proxies.CommonProxy;
import amerifrance.concoctions.registry.*;
import amerifrance.concoctions.util.EventHandlers;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.io.File;

@Mod(modid = ModInformation.ID, name = ModInformation.NAME, version = ModInformation.VERSION, dependencies = ModInformation.DEPEND, guiFactory = ModInformation.GUIFACTORY)
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
        configDir = new File(event.getModConfigurationDirectory() + "/" + ModInformation.NAME + ".cfg");
        ConfigHandler.init(configDir);

        ItemsRegistry.registerItems();
        BlocksRegistry.registerBlocks();
        TilesRegistry.registerTiles();
        ModEntities.registerEntities();
        ModConcoctions.registerBasicConcoctions();
        ModConcoctions.registerCompoundConcoctions();
        ModIngredients.registerIngredients();
        ModConcoctions.registerConcoctionRecipes();
        ModHeatSources.registerHeatSources();
        ModCoatedItems.registerCoatedItems();

        GuideConcoctions.setConcoctionBook();
        PacketHandler.registerPackets();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        EventHandlers.register();
        NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);
        proxy.registerRenders();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }
}
