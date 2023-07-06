package codes.matthewp.sukr.event;

import codes.matthewp.sukr.SimUKraft;
import codes.matthewp.sukr.util.KeyBinding;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SimUKraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ForgeClientEvents {
    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (KeyBinding.DEBUG_KEY.consumeClick()) {
        }
    }

    @SubscribeEvent
    public static void onOverlay(RenderGuiOverlayEvent event) {
        event.getGuiGraphics().drawString(Minecraft.getInstance().font, Component.literal("Day 1").withStyle(ChatFormatting.WHITE), 1, 1, 1);
        event.getGuiGraphics().drawString(Minecraft.getInstance().font, Component.literal("Population 1").withStyle(ChatFormatting.WHITE), 1, 11, 1);
        event.getGuiGraphics().drawString(Minecraft.getInstance().font, Component.literal("Credits: 20.0").withStyle(ChatFormatting.WHITE), 1, 21, 1);
    }
}
