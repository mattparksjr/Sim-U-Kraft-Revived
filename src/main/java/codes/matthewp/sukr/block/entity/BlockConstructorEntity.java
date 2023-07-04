package codes.matthewp.sukr.block.entity;

import codes.matthewp.sukr.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;

public class BlockConstructorEntity extends BlockEntity {

    private String employee;

    public BlockConstructorEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.CONSTRUCTOR.get(), pos, state);
        employee = "";
    }

    public void tick() {

    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        this.employee = tag.getString("employee");
        super.load(tag);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.putString("employee", this.employee);
        super.saveAdditional(tag);
    }

    public UUID getEmployee() {
        if (Objects.equals(employee, "")) {
            return null;
        }
        return UUID.fromString(employee);
    }

    public void setEmployee(UUID employee) {
        this.employee = employee.toString();
    }
}
