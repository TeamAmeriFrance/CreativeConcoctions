package amerifrance.concoctions;

import amerifrance.concoctions.api.concoctions.Concoction;
import amerifrance.concoctions.api.registry.ConcoctionsRegistry;
import net.minecraftforge.common.config.Configuration;
import sun.misc.Launcher;

import java.io.File;
import java.net.URL;

public class ConfigHandler {

    public static Configuration config;

    // Settings
    public static boolean enableLogging;

    public static String[] customHeatSources;
    public static String[] customHeatSourcesDefault = {"minecraft:redstone_torch:0:2400:15", "minecraft:torch:0:1200:30", "minecraft:fire:0:600:60", "minecraft:flowing_lava:0:300:120", "minecraft:lava:0:150:240"};

    public static String[] customCoatedItems;
    public static String[] customCoatedItemDefault = {"minecraft:stick:32"};

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

        category = "Concoctions.BasicConcoctions";
        config.addCustomCategoryComment(category, "Enable/Disable basic concoctions here");
        checkConcoctions("amerifrance.concoctions.concoctions.basic", category);

        category = "Concoctions.CompoundConcoctions";
        config.addCustomCategoryComment(category, "Enable/Disable compound concoctions here");
        checkConcoctions("amerifrance.concoctions.concoctions.compound", category);

        category = "Ingredients";
        config.addCustomCategoryComment(category, "All settings related to Ingredients");

        category = "Heat Sources";
        config.addCustomCategoryComment(category, "All settings related to Heat Sources");
        customHeatSources = config.getStringList("customHeatSources", category, customHeatSourcesDefault, "Custom heat source blocks.\nSyntax is: modid:name:meta:tickWait:maxHeat\nmodid = Unique ID of the block's mod.\nname = The registered blockname.\nmeta = Meta data of the block. If none is shown, use 0.\ntickWait = How many ticks to wait between heat updates\nmaxHeat = Maximum heat the block can provide.\n");

        category = "Coated Items";
        config.addCustomCategoryComment(category, "All custom Coated Items");
        customCoatedItems = config.getStringList("customCoatedItems", category, customCoatedItemDefault, "Custom coated items.\nSyntax is: modid:name:maxUses\nmodid = Unique ID of the item's mod.\nname = The registered item name.\nmaxUses = The amount of hits each concoction gives.");

        config.save();
    }

    public static void checkConcoctions(String packageName, String category) {
        String name = packageName;
        if (!name.startsWith("/"))
            name = "/" + name;

        name = name.replace('.', '/');
        URL url = Launcher.class.getResource(name);
        File directory = new File(url.getFile());

        if (directory.exists()) {
            String[] files = directory.list();

            for (int i = 0; i < files.length; i++) {
                if (files[i].endsWith(".class")) {
                    String className = files[i].substring(0, files[i].length() - 6);

                    try {
                        Object o = Class.forName(packageName + "." + className).newInstance();

                        if (o instanceof Concoction)
                            ConcoctionsRegistry.enabledConcoctions.put((Concoction) o, config.getBoolean(className, category, true, "Enables the " + className.replace("Concoction", "") + " concoction."));

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
