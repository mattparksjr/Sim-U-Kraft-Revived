package codes.matthewp.sukr.gui.constructor;

import codes.matthewp.sukr.block.entity.BlockConstructorEntity;
import codes.matthewp.sukr.gui.ScreenGamemode;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ScreenConstructor extends Screen {

    private int buttonW;
    private int buttonH = 20;

    private BlockConstructorEntity entity;

    public ScreenConstructor(BlockConstructorEntity entity) {
        super(Component.literal("Constructor"));
        this.entity = entity;
    }

    @Override
    protected void init() {
        super.init();
        buttonW = (width - 20) / 3;

        this.addRenderableWidget(new Button.Builder(Component.literal("Done"), ScreenConstructor::pressDone).size(40, buttonH).pos(5, 5).build());

        this.addRenderableWidget(new Button.Builder(Component.literal("Hire builder"), ScreenConstructor::pressHire).size(buttonW, buttonH).pos((width - 20) / 3 - buttonW + 10, height / 2).build());
        this.addRenderableWidget(new Button.Builder(Component.literal("Choose building"), ScreenConstructor::pressBuild).size(buttonW, buttonH).pos((width - 20) / 3 + 10, height / 2).build());
        this.addRenderableWidget(new Button.Builder(Component.literal("Fire worker"), ScreenConstructor::pressFire).size(buttonW, buttonH).pos((width - 20) / 3 + buttonW + 10, height / 2).build());
        this.addRenderableWidget(new Button.Builder(Component.literal("Hire terraformer"), ScreenConstructor::pressTerraform).size(buttonW, buttonH).pos((width - 20) / 3 - buttonW + 10, height / 2 + buttonH).build());
        this.addRenderableWidget(new Button.Builder(Component.literal("Terraform area"), ScreenConstructor::pressTerraformArea).size(buttonW, buttonH).pos((width - 20) / 3 + 10, height / 2 + buttonH).build());
        this.addRenderableWidget(new Button.Builder(Component.literal("Show employees"), ScreenConstructor::pressShowEmployees).size(buttonW, buttonH).pos((width - 20) / 3 + buttonW + 10, height / 2 + buttonH).build());
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
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
