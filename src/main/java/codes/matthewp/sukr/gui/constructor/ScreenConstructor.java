package codes.matthewp.sukr.gui.constructor;

import codes.matthewp.sukr.block.entity.BlockConstructorEntity;
import codes.matthewp.sukr.gui.base.ScreenBase;
import codes.matthewp.sukr.net.PacketHandler;
import codes.matthewp.sukr.net.packet.sync.RequestHireBuilderInfoC2SPacket;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

public class ScreenConstructor extends ScreenBase {

    private static BlockConstructorEntity entity;
    private static Player player;
    private final int buttonH = 20;
    private int buttonW;

    public ScreenConstructor(BlockConstructorEntity entity, Player player) {
        super(Component.literal("Constructor"));
        ScreenConstructor.entity = entity;
        ScreenConstructor.player = player;
    }

    @Override
    protected void init() {
        super.init();
        buttonW = Math.min(150, (width - 20) / 3);

        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.button.done"), ScreenBase::closeGUI).size(40, buttonH).pos(5, 5).build());

        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.constructor.hire"), button -> {
            PacketHandler.sendToServer(new RequestHireBuilderInfoC2SPacket());
        }).size((width - 20) / 3, buttonH).pos((width - 20) / 3 - buttonW + 10, height / 2).build());

        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.constructor.title"), ScreenBase::closeGUI).size((width - 20) / 3, buttonH).pos((width - 20) / 3 + 10 , height / 2).build());
        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.constructor.fire"), ScreenBase::closeGUI).size((width - 20) / 3, buttonH).pos((width - 20) / 3 + buttonW + 10, height / 2).build());
        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.constructor.terraformer"), ScreenBase::closeGUI).size((width - 20) / 3, buttonH).pos((width - 20) / 3 - buttonW + 10, height / 2 + buttonH).build());
        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.constructor.terraform"), ScreenBase::closeGUI).size((width - 20) / 3, buttonH).pos((width - 20) / 3 + 10, height / 2 + buttonH).build());
        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.constructor.showemp"), ScreenBase::closeGUI).size((width - 20) / 3, buttonH).pos((width - 20) / 3 + buttonW + 10 , height / 2 + buttonH).build());
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        ((Button) this.renderables.get(4)).active = false;
        ((Button) this.renderables.get(5)).active = false;

        if (entity.getEmployee() == null) {
            ((Button) this.renderables.get(3)).active = false;
        }

        drawCenteredString(graphics, Component.translatable("simukraftr.gui.constructor.title").withStyle(ChatFormatting.WHITE), width / 2, height / 3 - 60);
        drawCenteredString(graphics, Component.translatable("simukraftr.gui.constructor.choosetask").withStyle(ChatFormatting.YELLOW), width / 2, height / 3 + 20);

        super.render(graphics, mouseX, mouseY, partialTick);
    }
}
