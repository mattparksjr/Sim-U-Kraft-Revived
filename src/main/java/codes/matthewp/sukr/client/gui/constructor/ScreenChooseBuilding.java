package codes.matthewp.sukr.client.gui.constructor;

import codes.matthewp.sukr.client.gui.base.ScreenBase;
import codes.matthewp.sukr.data.structure.StructureCategory;
import codes.matthewp.sukr.data.structure.StructurePacketData;
import codes.matthewp.sukr.util.ClientUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ScreenChooseBuilding extends ScreenBase {

    protected BlockPos pos;
    protected List<StructurePacketData> data;
    protected StructureCategory category;
    private int buildingOffset = 0;

    public ScreenChooseBuilding(BlockPos pos, StructureCategory category, List<StructurePacketData> data) {
        super(Component.empty());
        this.pos = pos;
        this.category = category;
        this.data = data;
        setButtonWidth(120);
    }

    @Override
    protected void init() {
        super.init();
        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.button.done"), ScreenBase::closeGUI).size(smallButtonWidth, buttonHeight).pos(5, 5).build());
        this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.button.back"), $0 -> ClientUtil.showChooseType(pos, minecraft.player)).size(smallButtonWidth, buttonHeight).pos(45, 5).build());

        EditBox search = new EditBox(font, width / 2 - 50, height - 30, 100, 20, Component.empty());
        search.setFocused(true);
        search.setMaxLength(10);
        search.setResponder(s -> {
        });
        this.addRenderableWidget(search);

        if (data.isEmpty()) {
            this.addRenderableWidget(new Button.Builder(Component.translatable("simukraftr.gui.constructor.nobuildings"), $0 -> {
            }).pos(10, 60).size(300, buttonHeight).build());
        }

        int x = 10, y = 60;

        for (int i = 0; i < data.size(); i++) {
            StructurePacketData struct = data.get(i);
            this.addRenderableWidget(new Button.Builder(Component.literal(struct.getName()), $0 -> {
            }).pos(x, y).size(buttonWidth, buttonHeight).build());
            this.addRenderableWidget(new Button.Builder(Component.literal(struct.getSize().getX() + " x " + struct.getSize().getY() + " x " + struct.getSize().getZ()), $0 -> {
            }).pos(x, y + 48).size(buttonWidth, buttonHeight).build());
            this.addRenderableWidget(new Button.Builder(Component.literal(String.valueOf(struct.getCost())), $0 -> {
            }).pos(x, y + 32).size(buttonWidth, buttonHeight).build());
            this.addRenderableWidget(new Button.Builder(Component.literal(struct.getAuthor()), $0 -> {
            }).pos(x, y + 16).size(buttonWidth, buttonHeight).build());

            x = x + buttonWidth;

            if((x + buttonWidth) > width) {
                x = 10;
                y += 71;
            }

            if((y + (buttonHeight * 4) > height)) {
                // Need a new page

            }
        }
    }


    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        drawCenteredString(graphics, Component.translatable("simukraftr.gui.constructor.title").withStyle(ChatFormatting.WHITE), width / 2, height / 3 - 60);
        drawCenteredString(graphics, Component.translatable("simukraftr.gui.constructor.choosebuilding", category.toString().toLowerCase()).withStyle(ChatFormatting.YELLOW), width / 2, height / 3 - 40);

        ((Button) this.renderables.get(4)).active = false;
        ((Button) this.renderables.get(5)).active = false;
        ((Button) this.renderables.get(6)).active = false;

        super.render(graphics, mouseX, mouseY, partialTick);
    }
}
