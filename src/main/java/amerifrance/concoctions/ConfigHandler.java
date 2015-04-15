package amerifrance.concoctions;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {

    public static Configuration config;

    // Settings
    public static boolean enableLogging;

    public static String[] customHeatSources;
    public static String[] customHeatSourcesDefault = { "minecraft:redstone_torch:0:2400:15", "minecraft:torch:0:1200:30", "minecraft:fire:0:600:60", "minecraft:flowing_lava:0:300:120", "minecraft:lava:0:150:240" };

    public static void init(File file) {
        config = new Configuration(file);
        syncConfig();
    }

    public static void syncConfig() {
        String category;

        category = "General";
        config.addCustomCategoryComment(category, "Miscellaneous settings.");
        enableLogging = config.getBoolean("enableLogging", category, true, "Enables additional information in the console.");

        category = "Concoctions";
        config.addCustomCategoryComment(category, "All settings related to Concoctions");

        category = "Ingredients";
        config.addCustomCategoryComment(category, "All settings related to Ingredients");

        category = "Heat Sources";
        config.addCustomCategoryComment(category, "All settings related to Heat Sources");
        customHeatSources = config.getStringList("customHeatSources", category, customHeatSourcesDefault, "Custom heat source blocks.\nSyntax is: modid:name:meta:tickWait:maxHeat\nmodid = Unique ID of the block's mod.\nname = The registered blockname.\nmeta = Meta data of the block. If none is shown, use 0.\ntickWait = How many ticks to wait between heat updates\nmaxHeat = Maximum heat the block can provide.\n");

        config.save();
    }
}
