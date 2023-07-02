package codes.matthewp.sukr.util;

import codes.matthewp.sukr.SimUKraft;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {

    public static final String KEY_CATEGORY = "key.category." + SimUKraft.MODID;

    public static final String KEY_DEBUG = "key.category.debug";

    public static final KeyMapping DEBUG_KEY = new KeyMapping(KEY_DEBUG, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_V, KEY_CATEGORY);
}
