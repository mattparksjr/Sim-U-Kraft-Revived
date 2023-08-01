package codes.matthewp.sukr.gui.constructor;

import codes.matthewp.sukr.block.entity.BlockConstructorEntity;
import codes.matthewp.sukr.gui.ScreenGamemode;
import codes.matthewp.sukr.net.PacketHandler;
import codes.matthewp.sukr.net.packet.sync.RequestHireBuilderInfoC2SPacket;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

public class ScreenConstructor extends Screen {

    private int buttonW;
    private final int buttonH = 20;

    private static BlockConstructorEntity entity;
    private static Player player;

    public ScreenConstructor(BlockConstructorEntity entity, Player player) {
        super(Component.literal("Constructor"));
        ScreenConstructor.entity = entity;
        ScreenConstructor.player = player;
    }

    @Override
    protected void init() {
        super.init();
        buttonW = Math.min(150, (width - 20) / 3);

        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.button.done"), ScreenConstructor::pressDone).size(40, buttonH).pos(5, 5).build());

        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.constructor.hire"), ScreenConstructor::pressHire).size(buttonW, buttonH).pos((width - 20) / 3 - buttonW + 10, height / 2).build());
        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.constructor.title"), ScreenConstructor::pressBuild).size(buttonW, buttonH).pos((width - 20) / 3 + 10, height / 2).build());
        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.constructor.fire"), ScreenConstructor::pressFire).size(buttonW, buttonH).pos((width - 20) / 3 + buttonW + 10, height / 2).build());
        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.constructor.terraformer"), ScreenConstructor::pressTerraform).size(buttonW, buttonH).pos((width - 20) / 3 - buttonW + 10, height / 2 + buttonH).build());
        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.constructor.terraform"), ScreenConstructor::pressTerraformArea).size(buttonW, buttonH).pos((width - 20) / 3 + 10, height / 2 + buttonH).build());
        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.constructor.showemp"), ScreenConstructor::pressShowEmployees).size(buttonW, buttonH).pos((width - 20) / 3 + buttonW + 10, height / 2 + buttonH).build());
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);
        for (int i = 0; i <= 6; i++) {
            ((Button) this.renderables.get(i)).visible = true;
        }

        ((Button) this.renderables.get(4)).active = false;
        ((Button) this.renderables.get(5)).active = false;

        if (entity.getEmployee() == null) {
            ((Button) this.renderables.get(3)).active = false;
        }

        graphics.drawCenteredString(this.font, Component.translatable("simukraftr.gui.constructor.title").withStyle(ChatFormatting.WHITE), width / 2, height / 3 - 60, 1);
        graphics.drawCenteredString(this.font, Component.translatable("simukraftr.gui.constructor.choosetask").withStyle(ChatFormatting.YELLOW), width / 2, height / 3 + 20, 1);

        super.render(graphics, mouseX, mouseY, partialTick);
    }

    private static void pressDone(Button button) {
        Minecraft.getInstance().setScreen(null);
    }

    private static void pressHire(Button button) {
        Minecraft.getInstance().setScreen(null);
        PacketHandler.sendToServer(new RequestHireBuilderInfoC2SPacket());
    }

    private static void pressBuild(Button button) {
        Minecraft.getInstance().setScreen(null);
    }

    private static void pressFire(Button button) {
        Minecraft.getInstance().setScreen(null);
    }

    private static void pressTerraformArea(Button button) {
        Minecraft.getInstance().setScreen(null);
    }

    private static void pressTerraform(Button button) {
        Minecraft.getInstance().setScreen(null);
    }

    private static void pressShowEmployees(Button button) {
        Minecraft.getInstance().setScreen(new ScreenGamemode());
    }
}
