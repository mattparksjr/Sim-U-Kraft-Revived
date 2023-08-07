package codes.matthewp.sukr.event;

import codes.matthewp.sukr.SimUKraft;
import codes.matthewp.sukr.data.ClientSimData;
import codes.matthewp.sukr.data.player.PlayerDataProvider;
import codes.matthewp.sukr.util.KeyBinding;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
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
            Minecraft.getInstance().player.getCapability(PlayerDataProvider.PLAYER_DATA).ifPresent(data -> {
                Minecraft.getInstance().player.sendSystemMessage(Component.literal("Data: " + data.getMoney()));
            });
        }
    }

    @SubscribeEvent
    public static void onOverlay(RenderGuiOverlayEvent event) {
        int pop;
        if(ClientSimData.getFaction() != null) {
            pop = ClientSimData.getFaction().getData().getFolks().size();
        } else {
            pop = 0;
        }
        Minecraft.getInstance().player.getCapability(PlayerDataProvider.PLAYER_DATA).ifPresent(data ->
                event.getGuiGraphics().drawString(
                        Minecraft.getInstance().font,
                        Component.literal(I18n.get("simukraftr.gui.overlay", "Sun", String.valueOf(pop), data.getMoney())).withStyle(ChatFormatting.WHITE),
                        1, 1, 1));
    }
}
