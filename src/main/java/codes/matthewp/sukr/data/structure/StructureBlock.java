package codes.matthewp.sukr.data.structure;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class StructureBlock {

    private BlockPos pos;
    private BlockState state;

    public StructureBlock(BlockPos blockPos, BlockState state) {
        this.pos = blockPos;
        this.state = state;
    }

    public void setPos(BlockPos pos) {
        this.pos = pos;
    }

    public void setState(BlockState state) {
        this.state = state;
    }

    public BlockPos getPos() {
        return pos;
    }

    public BlockState getState() {
        return state;
    }
}
