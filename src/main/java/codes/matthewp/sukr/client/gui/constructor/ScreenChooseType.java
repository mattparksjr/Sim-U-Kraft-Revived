package codes.matthewp.sukr.client.gui.constructor;

import codes.matthewp.sukr.client.gui.base.ScreenBase;
import codes.matthewp.sukr.data.structure.StructureCategory;
import codes.matthewp.sukr.util.ClientUtil;
import io.netty.channel.epoll.Epoll;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

public class ScreenChooseType extends ScreenBase {

    protected BlockPos pos;
    protected Player player;

    public ScreenChooseType(BlockPos pos, Player player) {
        super(Component.empty());
        this.pos = pos;
        this.player = player;
    }

    @Override
    protected void init() {
        super.init();

        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.button.done"), ScreenBase::closeGUI).size(smallButtonWidth, buttonHeight).pos(5, 5).build());
        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.button.back"), $0 -> ClientUtil.showConstructor(pos, player)).size(smallButtonWidth, buttonHeight).pos(45, 5).build());

        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.building.type.residential"),
                $0 -> onPress(StructureCategory.RESIDENTIAL)).pos((width / 2) - 200, 150).size(buttonWidth, buttonHeight).build());
        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.building.type.commercial"),
                $0 -> onPress(StructureCategory.COMMERCIAL)).pos((width / 2) - 100, 150).size(buttonWidth, buttonHeight).build());
        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.building.type.industrial"),
                $0 -> onPress(StructureCategory.INDUSTRIAL)).pos((width / 2), 150).size(buttonWidth, buttonHeight).build());
        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.building.type.other"),
                $0 -> onPress(StructureCategory.OTHER)).pos((width / 2) + 100, 150).size(buttonWidth, buttonHeight).build());

        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.building.type.decorative"),
                $0 -> onPress(StructureCategory.DECORATIVE)).pos((int) ((width / 2) - (buttonWidth * 1.5)), 190).size(buttonWidth, buttonHeight).build());
        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.building.type.special"),
                $0 -> onPress(StructureCategory.SPECIAL)).pos((width / 2) - (buttonWidth / 2), 190).size(buttonWidth, buttonHeight).build());
       this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.building.type.administrative"),
                $0 -> onPress(StructureCategory.ADMINISTRATIVE)).pos((width / 2) + (buttonWidth / 2), 190).size(buttonWidth, buttonHeight).build());
    }

    public void onPress(StructureCategory category) {

    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        drawCenteredString(graphics, Component.translatable("simukraftr.gui.constructor.title").withStyle(ChatFormatting.WHITE), width / 2, height / 3 - 60);
        drawCenteredString(graphics, Component.translatable("simukraftr.gui.constructor.choosebuildingtype").withStyle(ChatFormatting.YELLOW), width / 2, height / 3 + 20);
        super.render(graphics, mouseX, mouseY, partialTick);
    }
}
