package codes.matthewp.sukr.util;

import codes.matthewp.sukr.block.entity.BlockConstructorEntity;
import codes.matthewp.sukr.gui.constructor.ScreenConstructor;
import codes.matthewp.sukr.gui.ScreenGamemode;
import net.minecraft.client.Minecraft;

public class ClientUtil {

    public static void showGamemodeSelect() {
        Minecraft.getInstance().setScreen(new ScreenGamemode());
    }

    public static void showConstructor(BlockConstructorEntity entity) {
        Minecraft.getInstance().setScreen(new ScreenConstructor(entity));
    }
}
