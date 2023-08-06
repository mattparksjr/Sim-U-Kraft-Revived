package codes.matthewp.sukr.client.gui.constructor;

import codes.matthewp.sukr.SimUKraft;
import codes.matthewp.sukr.client.gui.base.ScreenBase;
import codes.matthewp.sukr.data.structure.Structure;
import codes.matthewp.sukr.data.structure.StructureCategory;
import codes.matthewp.sukr.data.structure.StructureData;
import codes.matthewp.sukr.data.structure.StructurePacketData;
import codes.matthewp.sukr.util.ClientUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.MultilineTextField;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ScreenChooseBuilding extends ScreenBase {

    protected BlockPos pos;
    protected List<StructurePacketData> data;
    protected StructureCategory category;

    public ScreenChooseBuilding(BlockPos pos, StructureCategory category, List<StructurePacketData> data) {
        super(Component.empty());
        this.pos = pos;
        this.category = category;
        this.data = data;
    }

    @Override
    protected void init() {
        super.init();
        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.button.done"), ScreenBase::closeGUI).size(smallButtonWidth, buttonHeight).pos(5, 5).build());
        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.button.back"), $0 -> ClientUtil.showChooseType(pos, minecraft.player)).size(smallButtonWidth, buttonHeight).pos(45, 5).build());

        EditBox search = new EditBox(font, width / 2 - 50, height - 30, 100, 20, Component.empty());
        search.setFocused(true);
        search.setMaxLength(10);
        this.addRenderableWidget(search);

        for(StructurePacketData part : data) {
            SimUKraft.LOGGER.debug("Have part: " + part.getId() + " -----" + part.getName());
        }
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        drawCenteredString(graphics, Component.translatable("simukraftr.gui.constructor.title").withStyle(ChatFormatting.WHITE), width / 2, height / 3 - 60);
        drawCenteredString(graphics, Component.translatable("simukraftr.gui.constructor.choosebuilding", category.toString().toLowerCase()).withStyle(ChatFormatting.YELLOW), width / 2, height / 3 - 40);
        super.render(graphics, mouseX, mouseY, partialTick);
    }
}
