package amerifrance.concoctions.network;

import amerifrance.concoctions.ModInformation;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler {

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ModInformation.CHANNEL);

    public static void registerPackets() {
        INSTANCE.registerMessage(PacketFireBall.class, PacketFireBall.class, 0, Side.SERVER);
        INSTANCE.registerMessage(PacketOpenInventory.class, PacketOpenInventory.class, 1, Side.SERVER);
    }
}
