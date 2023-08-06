package codes.matthewp.sukr.client.gui.constructor;

import codes.matthewp.sukr.client.gui.base.ScreenBase;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public class ScreenChooseType extends ScreenBase {

    public ScreenChooseType() {
        super(Component.empty());
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        drawCenteredString(graphics, Component.translatable("simukraftr.gui.constructor.title").withStyle(ChatFormatting.WHITE), width / 2, height / 3 - 60);
        drawCenteredString(graphics, Component.translatable("simukraftr.gui.constructor.choosetask").withStyle(ChatFormatting.YELLOW), width / 2, height / 3 + 20);
        super.render(graphics, mouseX, mouseY, partialTick);
    }
}
