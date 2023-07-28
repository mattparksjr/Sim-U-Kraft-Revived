package codes.matthewp.sukr.util;

import codes.matthewp.sukr.block.entity.BlockConstructorEntity;
import codes.matthewp.sukr.gui.ScreenGamemode;
import codes.matthewp.sukr.gui.constructor.ScreenConstructor;
import codes.matthewp.sukr.gui.constructor.ScreenHireBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

public class ClientUtil {

    public static void showGamemodeSelect() {
        Minecraft.getInstance().setScreen(new ScreenGamemode());
    }

    public static void showConstructor(BlockConstructorEntity entity, Player player) {
        Minecraft.getInstance().setScreen(new ScreenConstructor(entity, player));
    }

    public static void openHireBuilderGUI() {
        Minecraft.getInstance().setScreen(new ScreenHireBuilder());
    }
}
