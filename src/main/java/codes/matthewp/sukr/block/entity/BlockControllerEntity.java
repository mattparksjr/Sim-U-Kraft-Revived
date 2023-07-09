package codes.matthewp.sukr.block.entity;

import codes.matthewp.sukr.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class BlockControllerEntity extends BlockEntity {

    private String homeOwner;

    public BlockControllerEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.CONTROLLER.get(), pos, state);
        homeOwner = "";
    }

    public void tick() {

    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        this.homeOwner = tag.getString("homeOwner");
        super.load(tag);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.putString("homeOwner", this.homeOwner);
        super.saveAdditional(tag);
    }

    public String getHomeOwner() {
        return homeOwner;
    }

    public void setHomeOwner(String homeOwner) {
        this.homeOwner = homeOwner;
    }
}
