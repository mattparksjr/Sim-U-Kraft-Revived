package codes.matthewp.sukr.net.packet.update;

import codes.matthewp.sukr.block.entity.BlockConstructorEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.server.ServerLifecycleHooks;

import java.util.function.Supplier;

public class FireWorkerC2SPacket {

    private final BlockPos pos;

    public FireWorkerC2SPacket(BlockPos pos) {
        this.pos = pos;
    }

    public FireWorkerC2SPacket(FriendlyByteBuf buf) {
        this.pos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            if (ServerLifecycleHooks.getCurrentServer() != null) {
                BlockConstructorEntity blockEntity = (BlockConstructorEntity) ServerLifecycleHooks.getCurrentServer().overworld().getBlockEntity(pos);
                if (blockEntity != null) {
                    blockEntity.fireEmployee();
                }
            }
        });
        return true;
    }
}
