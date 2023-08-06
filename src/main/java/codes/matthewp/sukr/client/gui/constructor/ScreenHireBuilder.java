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
        setButtonWidth((width - 10) / 4);

        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.button.done"),
                ScreenBase::closeGUI).size(smallButtonWidth, buttonHeight).pos(5, 5).build());
        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.constructor.cancel"),
                ScreenBase::closeGUI).size((width - 20) / 2, buttonHeight).pos(10, height - (20 + buttonHeight)).build());
        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.constructor.ok"),
                ScreenBase::closeGUI).size((width - 20) / 2, buttonHeight).pos(width / 2, height - (20 + buttonHeight)).build());

        int rowCount = 0;
        int currentRow = 0;
        for (UUID uuid : map.keySet()) {
            if(rowCount == 3) {
                currentRow++;
                rowCount = 0;
            }

            this.addRenderableWidget(new Button.Builder(Component.literal(map.get(uuid)), button -> {
                PacketHandler.sendToServer(new SetWorkerC2SPacket(pos, uuid));
            }).size(buttonWidth, buttonHeight).pos(5 + (rowCount * buttonWidth), height / 3 + 10 + (currentRow * buttonHeight)).build());
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
