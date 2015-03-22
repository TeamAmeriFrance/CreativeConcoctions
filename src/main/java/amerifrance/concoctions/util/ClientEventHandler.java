package amerifrance.concoctions.util;

import amerifrance.concoctions.ModInformation;
import amerifrance.concoctions.network.PacketFireBall;
import amerifrance.concoctions.network.PacketHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Keyboard;

public class ClientEventHandler {

    public static KeyBinding fireball /*PUUN*/ = new KeyBinding(StatCollector.translateToLocal("keybind.fireball"), Keyboard.KEY_F, ModInformation.NAME);

    public ClientEventHandler() {
        ClientRegistry.registerKeyBinding(fireball);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (fireball.isPressed()) {
            PacketHandler.INSTANCE.sendToServer(new PacketFireBall());
        }
    }
}
