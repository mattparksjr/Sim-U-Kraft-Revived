package codes.matthewp.sukr.net.packet.sync;

import codes.matthewp.sukr.data.SimDataManager;
import codes.matthewp.sukr.net.PacketHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class RequestHireBuilderInfoC2SPacket {


    public RequestHireBuilderInfoC2SPacket() {
    }

    public RequestHireBuilderInfoC2SPacket(FriendlyByteBuf buf) {
    }

    public void toBytes(FriendlyByteBuf buf) {
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            PacketHandler.sendToPlayer(new SyncAvailableBuildersS2CPacket(SimDataManager.get(context.getSender().serverLevel()).getData().getPlayerFaction(context.getSender().getUUID())), context.getSender());
        });
        return true;
    }
}
