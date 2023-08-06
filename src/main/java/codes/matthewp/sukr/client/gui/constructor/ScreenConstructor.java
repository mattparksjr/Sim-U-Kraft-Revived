package codes.matthewp.sukr.client.gui.constructor;

import codes.matthewp.sukr.SimUKraft;
import codes.matthewp.sukr.block.entity.BlockConstructorEntity;
import codes.matthewp.sukr.client.gui.base.ScreenBase;
import codes.matthewp.sukr.net.PacketHandler;
import codes.matthewp.sukr.net.packet.sync.RequestHireBuilderInfoC2SPacket;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;

public class ScreenConstructor extends ScreenBase {

    private BlockPos entityPos;
    private Player player;
    private final int buttonH = 20;
    private int buttonW;

    private BlockConstructorEntity entity;

    public ScreenConstructor(BlockPos entityPos, Player player) {
        super(Component.literal("Constructor"));
        this.entityPos = entityPos;
        this.player = player;
    }

    @Override
    protected void init() {
        super.init();
        if(this.minecraft == null) return;
        Level level = this.minecraft.level;
        if(level == null) return;

        BlockEntity entity = level.getBlockEntity(this.entityPos);
        if(entity instanceof BlockConstructorEntity blockConstructorEntity) {
            this.entity = blockConstructorEntity;
        } else {
            SimUKraft.LOGGER.error("BlockEntity at {} is not correct type for ScreenConstructor", this.entityPos);
        }

        buttonW = Math.min(150, (width - 20) / 3);

        SimUKraft.LOGGER.debug("Block: " + player.level().getBlockEntity(entityPos) + " ---- " + player.level().getBlockEntity(entityPos).getType() + " ---- " + player.level().getBlockEntity(entityPos).getUpdateTag());
        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.button.done"), ScreenBase::closeGUI).size(40, buttonH).pos(5, 5).build());

        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.constructor.hire"), button -> {
            PacketHandler.sendToServer(new RequestHireBuilderInfoC2SPacket(entityPos));
        }).size((width - 20) / 3, buttonH).pos((width - 20) / 3 - buttonW + 10, height / 2).build());

        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.constructor.choose"), ScreenBase::closeGUI).size((width - 20) / 3, buttonH).pos((width - 20) / 3 + 10 , height / 2).build());
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
            ((Button) this.renderables.get(2)).active = false;
        } else {
            ((Button) this.renderables.get(1)).active = false;
            ((Button) this.renderables.get(2)).active = true;
        }

        drawCenteredString(graphics, Component.translatable("simukraftr.gui.constructor.title").withStyle(ChatFormatting.WHITE), width / 2, height / 3 - 60);
        drawCenteredString(graphics, Component.translatable("simukraftr.gui.constructor.choosetask").withStyle(ChatFormatting.YELLOW), width / 2, height / 3 + 20);

        super.render(graphics, mouseX, mouseY, partialTick);
    }
}