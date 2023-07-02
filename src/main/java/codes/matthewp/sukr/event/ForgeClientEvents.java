package codes.matthewp.sukr.event;

import codes.matthewp.sukr.SimUKraft;
import codes.matthewp.sukr.util.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SimUKraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ForgeClientEvents {
    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (KeyBinding.DEBUG_KEY.consumeClick()) {
        }
    }
}
