package codes.matthewp.sukr.util;

import codes.matthewp.sukr.gui.ScreenGamemode;
import net.minecraft.client.Minecraft;

public class ClientUtil {

    public static void showGamemodeSelect() {
        Minecraft.getInstance().setScreen(new ScreenGamemode());
    }
}
