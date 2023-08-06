package codes.matthewp.sukr.net.packet.sync;

import codes.matthewp.sukr.SimUKraft;
import codes.matthewp.sukr.data.SimDataManager;
import codes.matthewp.sukr.net.PacketHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class RequestHireBuilderInfoC2SPacket {

    private BlockPos pos;

    public RequestHireBuilderInfoC2SPacket(BlockPos pos) {
        this.pos = pos;
    }

    public RequestHireBuilderInfoC2SPacket(FriendlyByteBuf buf) {
        pos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            PacketHandler.sendToPlayer(new SyncAvailableBuildersS2CPacket(pos, SimDataManager.get(context.getSender().serverLevel()).getData().getPlayerFaction(context.getSender().getUUID())), context.getSender());
        });
        return true;
    }
}
