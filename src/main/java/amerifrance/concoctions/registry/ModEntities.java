package amerifrance.concoctions.registry;

import amerifrance.concoctions.CreativeConcoctions;
import amerifrance.concoctions.entities.EntityConcoction;
import cpw.mods.fml.common.registry.EntityRegistry;

public class ModEntities {

    public static void registerEntities() {
        EntityRegistry.registerModEntity(EntityConcoction.class, "EntityConcoction", 0, CreativeConcoctions.instance, 80, 3, true);
    }
}
