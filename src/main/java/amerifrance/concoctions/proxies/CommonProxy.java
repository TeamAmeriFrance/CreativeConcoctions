package amerifrance.concoctions.proxies;

import amerifrance.concoctions.gui.GuiConcoctions;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class CommonProxy implements IGuiHandler {

    public World getClientWorld() {
        return null;
    }

    public void setConcoctionBook() {
    }

    public void registerRenders() {
    }

    public void cauldronSplash(World world, double x, double y, double z) {
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            default:
                return null;
            case 0:
                return new GuiConcoctions(player);
        }
    }
}
