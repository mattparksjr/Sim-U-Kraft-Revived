package codes.matthewp.sukr.block.entity;

import codes.matthewp.sukr.SimUKraft;
import codes.matthewp.sukr.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.UUID;

public class BlockConstructorEntity extends BlockEntity {

    private String employee = "";
    private String owner = "";

    public BlockConstructorEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.CONSTRUCTOR.get(), pos, state);
    }

    public void tick() {

    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);

        CompoundTag data = tag.getCompound(SimUKraft.MODID);
        this.employee = data.getString("employee");
        this.owner = data.getString("owner");
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);

        var data = new CompoundTag();
        data.putString("employee", this.employee);
        data.putString("owner", this.owner);
        tag.put(SimUKraft.MODID, data);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        saveAdditional(tag);
        return tag;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public UUID getOwner() {
        if (Objects.equals(owner, "")) return null;
        return UUID.fromString(owner);
    }

    public void setOwner(UUID owner) {
        this.owner = owner.toString();
        setChanged();
        if (this.level != null) {
            this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
        }
    }

    public UUID getEmployee() {
        if (Objects.equals(employee, "")) return null;
        return UUID.fromString(employee);
    }

    public void setEmployee(UUID employee) {
        this.employee = employee.toString();
        setChanged();
        if (this.level != null) {
            this.level.sendBlockUpdated(this.worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
        }
    }
}
