package codes.matthewp.sukr.client.gui;

import codes.matthewp.sukr.data.player.faction.Faction;
import codes.matthewp.sukr.client.gui.base.ScreenBase;
import codes.matthewp.sukr.net.PacketHandler;
import codes.matthewp.sukr.net.packet.CreateFactionC2SPacket;
import codes.matthewp.sukr.net.packet.SetGamemodeC2SPacket;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public class ScreenGamemode extends ScreenBase {

    private final int buttonW = 200;
    private final int buttonH = 20;

    public ScreenGamemode() {
        super(Component.literal("Gamemode Selection"));
    }

    @Override
    protected void init() {
        super.init();

        this.addRenderableWidget(
                new Button.Builder(Component.translatable("simukraftr.gui.gamemode.donotrun"), button -> {
                    PacketHandler.sendToServer(new SetGamemodeC2SPacket(0));
                    Minecraft.getInstance().setScreen(null);
                }).size(buttonW, buttonH).pos(width / 2 - buttonW / 2, height / 3 - 40).build()
        );

        this.addRenderableWidget(
                new Button.Builder(Component.translatable("simukraftr.gui.gamemode.survival"), button -> {
                    PacketHandler.sendToServer(new SetGamemodeC2SPacket(1));
                    Faction faction = new Faction(Minecraft.getInstance().player.getUUID());
                    PacketHandler.sendToServer(new CreateFactionC2SPacket(faction));
                    Minecraft.getInstance().setScreen(null);
                }).size(buttonW, buttonH).pos(width / 2 - buttonW / 2, height / 3 + 10).build()
        );

        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.gamemode.creative"), button -> {
                    PacketHandler.sendToServer(new SetGamemodeC2SPacket(2));
                    Faction faction = new Faction(Minecraft.getInstance().player.getUUID());
                    PacketHandler.sendToServer(new CreateFactionC2SPacket(faction));
                    Minecraft.getInstance().setScreen(null);
                }).size(buttonW, buttonH).pos(width / 2 - buttonW / 2, height / 3 + 60).build()
        );
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {

        drawCenteredString(graphics, Component.translatable("simukraftr.gui.gamemode.welcome").withStyle(ChatFormatting.WHITE), width / 2, height / 3 - 70);
        drawCenteredString(graphics, Component.translatable("simukraftr.gui.gamemode.mode").withStyle(ChatFormatting.WHITE), width / 2, height / 3 - 60);

        drawCenteredString(graphics, Component.translatable("simukraftr.gui.gamemode.dnrdesc").withStyle(ChatFormatting.GOLD), width / 2, height / 3 - 15);
        drawCenteredString(graphics, Component.translatable("simukraftr.gui.gamemode.sdesc").withStyle(ChatFormatting.GOLD), width / 2, height / 3 + 35);
        drawCenteredString(graphics, Component.translatable("simukraftr.gui.gamemode.cdesc").withStyle(ChatFormatting.GOLD), width / 2, height / 3 + 85);

        super.render(graphics, mouseX, mouseY, partialTick);
    }
}
