package codes.matthewp.sukr.gui.constructor;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public class ScreenHireBuilder extends Screen {

    private final int buttonH = 20;
    private final int buttonW = 100;
    private final HashMap<UUID, String> map;

    public ScreenHireBuilder(HashMap<UUID, String> map) {
        super(Component.literal("Constructor"));
        this.map = map;
    }

    private static void pressDone(Button button) {
        Minecraft.getInstance().setScreen(null);
    }

    private static void pressCancel(Button button) {
        //  Minecraft.getInstance().setScreen(new ScreenConstructor(entity, player));
    }

    @Override
    protected void init() {
        super.init();
        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.button.done"), ScreenHireBuilder::pressDone).size(40, buttonH).pos(5, 5).build());
        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.constructor.cancel"),
                ScreenHireBuilder::pressCancel).size((width - 20) / 2, buttonH).pos(10, height - (20 + buttonH)).build());
        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.constructor.ok"),
                ScreenHireBuilder::pressCancel).size((width - 20) / 2, buttonH).pos(width / 2, height - (20 + buttonH)).build());

        // TODO: Buttons stack for multiple builders :-)
        for (UUID uuid : map.keySet()) {
            this.addRenderableWidget(new Button.Builder(Component.literal(map.get(uuid)), ScreenHireBuilder::pressDone).size(buttonW, buttonH).pos(5, height / 3 + 10).build());
        }

    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);

        graphics.drawCenteredString(this.font, Component.translatable("simukraftr.gui.constructor.title").withStyle(ChatFormatting.WHITE), width / 2, height / 3 - 60, 1);
        graphics.drawCenteredString(this.font, Component.translatable("simukraftr.gui.constructor.choosefolk").withStyle(ChatFormatting.YELLOW), width / 2, height / 3 - 20, 1);

        super.render(graphics, mouseX, mouseY, partialTick);
    }
}
