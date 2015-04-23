package amerifrance.concoctions.client.gui;

import amerifrance.concoctions.ModInformation;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.ConfigElement;

import java.util.ArrayList;
import java.util.List;

import static amerifrance.concoctions.ConfigHandler.config;

public class ConfigGui extends GuiConfig {

    public ConfigGui(GuiScreen parentScreen) {
        super(parentScreen, getConfigElements(parentScreen), ModInformation.ID, false, false, StatCollector.translateToLocal("gui." + ModInformation.ID + ".config.title"));
    }

    @SuppressWarnings("rawtypes")
    private static List<IConfigElement> getConfigElements(GuiScreen parent) {
        List<IConfigElement> list = new ArrayList<IConfigElement>();

        list.add(new ConfigElement(config.getCategory("General".toLowerCase())));
        list.add(new ConfigElement(config.getCategory("Concoctions".toLowerCase())));
        list.add(new ConfigElement(config.getCategory("Ingredients".toLowerCase())));
        list.add(new ConfigElement(config.getCategory("Heat Sources".toLowerCase())));

        return list;
    }
}
