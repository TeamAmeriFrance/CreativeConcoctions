package amerifrance.concoctions.util.event;

import amerifrance.concoctions.ModInformation;
import amerifrance.concoctions.client.gui.GuiConcoctions;
import amerifrance.concoctions.network.PacketFireBall;
import amerifrance.concoctions.network.PacketHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Keyboard;

public class KeyHandler {

    public static KeyBinding fireball = new KeyBinding(StatCollector.translateToLocal("keybind.fireball"), Keyboard.KEY_F, ModInformation.NAME);
    public static KeyBinding openConcoctionsGui = new KeyBinding(StatCollector.translateToLocal("keybind.open.concoctions.gui"), Keyboard.KEY_C, ModInformation.NAME);

    public KeyHandler() {
        ClientRegistry.registerKeyBinding(fireball);
        ClientRegistry.registerKeyBinding(openConcoctionsGui);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (fireball.isPressed()) {
            PacketHandler.INSTANCE.sendToServer(new PacketFireBall());
        } else if (openConcoctionsGui.isPressed()) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiConcoctions(Minecraft.getMinecraft().thePlayer));
        }
    }
}
