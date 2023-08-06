package codes.matthewp.sukr.util;

import codes.matthewp.sukr.client.gui.ScreenGamemode;
import codes.matthewp.sukr.client.gui.constructor.ScreenChooseBuilding;
import codes.matthewp.sukr.client.gui.constructor.ScreenChooseType;
import codes.matthewp.sukr.client.gui.constructor.ScreenConstructor;
import codes.matthewp.sukr.client.gui.constructor.ScreenHireBuilder;
import codes.matthewp.sukr.data.structure.StructureCategory;
import codes.matthewp.sukr.data.structure.StructurePacketData;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ClientUtil {

    public static void showGamemodeSelect() {
        Minecraft.getInstance().setScreen(new ScreenGamemode());
    }

    public static void showConstructor(BlockPos pos, Player player) {
        Minecraft.getInstance().setScreen(new ScreenConstructor(pos, player));
    }

    public static void showHire(BlockPos pos, HashMap<UUID, String> map) {
        Minecraft.getInstance().setScreen(new ScreenHireBuilder(pos, map));
    }

    public static void closeCurrentUI() {
        Minecraft.getInstance().setScreen(null);
    }

    public static void showChooseType(BlockPos pos, Player player) {
        Minecraft.getInstance().setScreen(new ScreenChooseType(pos, player));
    }

    public static void showChooseBuilding(BlockPos pos, List<StructurePacketData> structures, StructureCategory category) {
        Minecraft.getInstance().setScreen(new ScreenChooseBuilding(pos, category, structures));
    }
}
