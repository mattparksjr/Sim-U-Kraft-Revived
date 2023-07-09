package codes.matthewp.sukr.gui;

import codes.matthewp.sukr.net.PacketHandler;
import codes.matthewp.sukr.net.packet.SetGamemodeC2SPacket;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ScreenGamemode extends Screen {

    private final int buttonW = 200;
    private final int buttonH = 20;

    public ScreenGamemode() {
        super(Component.literal("Gamemode Selection"));
    }

    @Override
    protected void init() {
        super.init();

        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.gamemode.donotrun"), ScreenGamemode::pressDNR).size(buttonW, buttonH).pos(width / 2 - buttonW / 2, height / 3 - 40).build());
        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.gamemode.survival"), ScreenGamemode::pressSurvival).size(buttonW, buttonH).pos(width / 2 - buttonW / 2, height / 3 + 10).build());
        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.gamemode.creative"), ScreenGamemode::pressCreative).size(buttonW, buttonH).pos(width / 2 - buttonW / 2, height / 3 + 60).build());
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);

        graphics.drawCenteredString(this.font, Component.translatable("simukraftr.gui.gamemode.welcome").withStyle(ChatFormatting.WHITE), width / 2, height / 3 - 70, 1);
        graphics.drawCenteredString(this.font, Component.translatable("simukraftr.gui.gamemode.mode").withStyle(ChatFormatting.WHITE), width / 2, height / 3 - 60, 1);

        graphics.drawCenteredString(this.font, Component.translatable("simukraftr.gui.gamemode.dnrdesc").withStyle(ChatFormatting.GOLD), width / 2, height / 3 - 15, 1);
        graphics.drawCenteredString(this.font, Component.translatable("simukraftr.gui.gamemode.sdesc").withStyle(ChatFormatting.GOLD), width / 2, height / 3 + 35, 1);
        graphics.drawCenteredString(this.font, Component.translatable("simukraftr.gui.gamemode.cdesc").withStyle(ChatFormatting.GOLD), width / 2, height / 3 + 85, 1);


        super.render(graphics, mouseX, mouseY, partialTick);
    }

    private static void pressDNR(Button button) {
        PacketHandler.sendToServer(new SetGamemodeC2SPacket(0));
        Minecraft.getInstance().setScreen(null);
    }

    private static void pressSurvival(Button button) {
        PacketHandler.sendToServer(new SetGamemodeC2SPacket(1));
        Minecraft.getInstance().setScreen(null);
    }

    private static void pressCreative(Button button) {
        PacketHandler.sendToServer(new SetGamemodeC2SPacket(2));
        Minecraft.getInstance().setScreen(null);
    }
}
