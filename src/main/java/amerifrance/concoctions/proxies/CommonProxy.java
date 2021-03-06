package amerifrance.concoctions.proxies;

import amerifrance.concoctions.client.gui.GuiConcoctions;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class CommonProxy implements IGuiHandler {

    public World getClientWorld() {
        return null;
    }

    public void registerRenders() {
    }

    public void cauldronSplash(World world, double x, double y, double z, int stacksize) {
    }

    public void cauldronFumes(World world, double x, double y, double z) {
    }

    public void poisonousFume(World world, double x, double y, double z) {
    }

    public void cauldronMelt(World world, double x, double y, double z, int type) {
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
