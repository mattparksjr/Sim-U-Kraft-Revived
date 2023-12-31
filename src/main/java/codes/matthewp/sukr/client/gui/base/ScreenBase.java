package codes.matthewp.sukr.client.gui.base;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public class ScreenBase extends Screen {

    protected int buttonWidth = 100;
    protected int buttonHeight = 20;
    protected int smallButtonWidth = 40;

    protected ScreenBase(Component component) {
        super(component);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    public void drawCenteredString(GuiGraphics graphics, Component text, int x, int y) {
        this.drawCenteredString(graphics, text, x, y, 1);
    }

    public void drawCenteredString(GuiGraphics graphics, Component text, int x, int y, int z) {
        graphics.drawCenteredString(this.font, text, x, y, z);
    }

    public void drawString(GuiGraphics graphics, Component text, int x, int y, int z) {
        graphics.drawString(this.font, text, x, y, z);
    }

    public void setButtonHeight(int buttonHeight) {
        this.buttonHeight = buttonHeight;
    }

    public void setButtonWidth(int buttonWidth) {
        this.buttonWidth = buttonWidth;
    }

    public static void closeGUI(Button b) {
        Minecraft.getInstance().setScreen(null);
    }
}
