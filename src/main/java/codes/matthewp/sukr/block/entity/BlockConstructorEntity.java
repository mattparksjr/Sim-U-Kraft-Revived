package codes.matthewp.sukr.block.entity;

import codes.matthewp.sukr.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BlockConstructorEntity extends BlockEntity {



    public BlockConstructorEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.CONSTRUCTOR.get(), pos, state);
    }

    public void tick() {

    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        // this.data = tag.getData("data")
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        // tag.putData("data", this.data)
    }
}
