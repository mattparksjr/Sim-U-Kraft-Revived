package codes.matthewp.sukr.util;

import codes.matthewp.sukr.block.entity.BlockConstructorEntity;
import codes.matthewp.sukr.gui.ScreenGamemode;
import codes.matthewp.sukr.gui.constructor.ScreenConstructor;
import codes.matthewp.sukr.gui.constructor.ScreenHireBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.UUID;

public class ClientUtil {

    public static void showGamemodeSelect() {
        Minecraft.getInstance().setScreen(new ScreenGamemode());
    }

    public static void showConstructor(BlockConstructorEntity entity, Player player) {
        Minecraft.getInstance().setScreen(new ScreenConstructor(entity, player));
    }

    public static void openHireBuilderGUI(HashMap<UUID, String> map) {
        Minecraft.getInstance().setScreen(new ScreenHireBuilder(map));
    }
}
