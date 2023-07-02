package codes.matthewp.sukr.block.entity;

import codes.matthewp.sukr.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class BlockConstructorEntity extends BlockEntity {

    private UUID employee;

    public BlockConstructorEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.CONSTRUCTOR.get(), pos, state);
    }

    public void tick() {

    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        this.employee = tag.getUUID("employee");
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putUUID("employee", this.employee);
    }

    public UUID getEmployee() {
        return employee;
    }

    public void setEmployee(UUID employee) {
        this.employee = employee;
    }
}
