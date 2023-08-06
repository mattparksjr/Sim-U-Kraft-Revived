package codes.matthewp.sukr.client.gui.constructor;

import codes.matthewp.sukr.SimUKraft;
import codes.matthewp.sukr.client.gui.base.ScreenBase;
import codes.matthewp.sukr.net.PacketHandler;
import codes.matthewp.sukr.net.packet.update.SetWorkerC2SPacket;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public class ScreenHireBuilder extends ScreenBase {

    private final int buttonH = 20;
    private int buttonW = 100;
    private final HashMap<UUID, String> map;
    private final BlockPos pos;

    public ScreenHireBuilder(BlockPos pos, HashMap<UUID, String> map) {
        super(Component.literal("Constructor"));
        this.map = map;
        this.pos = pos;
    }

    @Override
    protected void init() {
        super.init();
        buttonW = (width - 10) / 4;

        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.button.done"),
                ScreenBase::closeGUI).size(40, buttonH).pos(5, 5).build());
        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.constructor.cancel"),
                ScreenBase::closeGUI).size((width - 20) / 2, buttonH).pos(10, height - (20 + buttonH)).build());
        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.constructor.ok"),
                ScreenBase::closeGUI).size((width - 20) / 2, buttonH).pos(width / 2, height - (20 + buttonH)).build());

        int rowCount = 0;
        int currentRow = 0;
        for (UUID uuid : map.keySet()) {
            if(rowCount == 3) {
                currentRow++;
                rowCount = 0;
            }

            this.addRenderableWidget(new Button.Builder(Component.literal(map.get(uuid)), button -> {
                PacketHandler.sendToServer(new SetWorkerC2SPacket(pos, uuid));
            }).size(buttonW, buttonH).pos(5 + (rowCount * buttonW), height / 3 + 10 + (currentRow * buttonH)).build());
            rowCount++;
        }

    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        drawCenteredString(graphics, Component.translatable("simukraftr.gui.constructor.title").withStyle(ChatFormatting.WHITE), width / 2, height / 3 - 60);
        drawCenteredString(graphics, Component.translatable("simukraftr.gui.constructor.choosefolk").withStyle(ChatFormatting.YELLOW), width / 2, height / 3 - 20);

        super.render(graphics, mouseX, mouseY, partialTick);
    }
}