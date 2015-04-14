package amerifrance.concoctions;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {

    public static Configuration config;

    // Settings
    public static boolean enableLogging;

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

        config.save();
    }
}
