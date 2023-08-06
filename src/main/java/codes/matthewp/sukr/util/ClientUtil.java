package codes.matthewp.sukr.util;

import codes.matthewp.sukr.block.entity.BlockConstructorEntity;
import codes.matthewp.sukr.client.gui.ScreenGamemode;
import codes.matthewp.sukr.client.gui.constructor.ScreenChooseType;
import codes.matthewp.sukr.client.gui.constructor.ScreenConstructor;
import codes.matthewp.sukr.client.gui.constructor.ScreenHireBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.UUID;

public class ClientUtil {

    public static void showGamemodeSelect() {
        Minecraft.getInstance().setScreen(new ScreenGamemode());
    }

    public static void showConstructor(BlockPos pos, Player player) {
        Minecraft.getInstance().setScreen(new ScreenConstructor(pos, player));
    }

    public static void openHireBuilderGUI(BlockPos pos, HashMap<UUID, String> map) {
        Minecraft.getInstance().setScreen(new ScreenHireBuilder(pos, map));
    }

    public static void closeCurrentUI() {
        Minecraft.getInstance().setScreen(null);
    }

    public static void openChooseType(BlockPos pos, Player player) {
        Minecraft.getInstance().setScreen(new ScreenChooseType(pos, player));
    }
}
