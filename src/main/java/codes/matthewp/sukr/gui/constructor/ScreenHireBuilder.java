package codes.matthewp.sukr.gui.constructor;

import codes.matthewp.sukr.block.entity.BlockConstructorEntity;
import codes.matthewp.sukr.data.ClientSimData;
import codes.matthewp.sukr.data.player.PlayerData;
import codes.matthewp.sukr.data.player.PlayerDataProvider;
import codes.matthewp.sukr.entity.EntityFolk;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

public class ScreenHireBuilder extends Screen {

   // private static BlockConstructorEntity entity;
  //  private static Player player;
    private final int buttonH = 20;
    private final int buttonW = 100;

    public ScreenHireBuilder(BlockConstructorEntity entity, Player player) {
        super(Component.literal("Constructor"));
       // ScreenHireBuilder.entity = entity;
//.player = player;
    }

    public ScreenHireBuilder() {
        super(Component.literal("Constructor"));
    }

    @Override
    protected void init() {
        super.init();
        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.button.done"), ScreenHireBuilder::pressDone).size(40, buttonH).pos(5, 5).build());
        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.constructor.cancel"),
                ScreenHireBuilder::pressCancel).size((width - 20) / 2, buttonH).pos(10, height - (20 + buttonH)).build());
        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.constructor.ok"),
                ScreenHireBuilder::pressCancel).size((width - 20) / 2, buttonH).pos(width / 2, height - (20 + buttonH)).build());

        Minecraft.getInstance().player.getCapability(PlayerDataProvider.PLAYER_DATA).ifPresent(playerData -> {
            for(EntityFolk folk : ClientSimData.getFaction().getData().getUnemployedFolks()) {
                this.addRenderableWidget(new Button.Builder(Component.literal(folk.getFullname()), ScreenHireBuilder::pressDone).size(buttonW, buttonH).pos(5, height / 3 + 50).build());
            }
        });

    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);

        graphics.drawCenteredString(this.font, Component.translatable("simukraftr.gui.constructor.title").withStyle(ChatFormatting.WHITE), width / 2, height / 3 - 60, 1);
        graphics.drawCenteredString(this.font, Component.translatable("simukraftr.gui.constructor.choosefolk").withStyle(ChatFormatting.YELLOW), width / 2, height / 3 + 20, 1);

        super.render(graphics, mouseX, mouseY, partialTick);
    }


    private static void pressDone(Button button) {
        Minecraft.getInstance().setScreen(null);
    }

    private static void pressCancel(Button button) {
      //  Minecraft.getInstance().setScreen(new ScreenConstructor(entity, player));
    }
}
