package codes.matthewp.sukr.net.packet.update;

import codes.matthewp.sukr.SimUKraft;
import codes.matthewp.sukr.block.entity.BlockConstructorEntity;
import codes.matthewp.sukr.data.SimData;
import codes.matthewp.sukr.entity.EntityFolk;
import codes.matthewp.sukr.net.PacketHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.server.ServerLifecycleHooks;

import java.util.UUID;
import java.util.function.Supplier;

public class SetWorkerC2SPacket {

    private final BlockPos pos;
    private final UUID workerID;

    public SetWorkerC2SPacket(BlockPos pos, UUID workerID) {
        this.pos = pos;
        this.workerID = workerID;
    }

    public SetWorkerC2SPacket(FriendlyByteBuf buf) {
        this.pos = buf.readBlockPos();
        this.workerID = buf.readUUID();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
        buf.writeUUID(workerID);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            if (ServerLifecycleHooks.getCurrentServer() != null) {
                BlockConstructorEntity blockEntity = (BlockConstructorEntity) ServerLifecycleHooks.getCurrentServer().overworld().getBlockEntity(pos);
                EntityFolk folk = (EntityFolk) ServerLifecycleHooks.getCurrentServer().overworld().getEntity(workerID);
                blockEntity.setEmployee(workerID);
                folk.getEntityData().set(EntityFolk.JOB_SITE, pos);
                PacketHandler.sendToPlayer(new WorkerSetS2CPacket(), context.getSender());
            }
        });
        return true;
    }
}
