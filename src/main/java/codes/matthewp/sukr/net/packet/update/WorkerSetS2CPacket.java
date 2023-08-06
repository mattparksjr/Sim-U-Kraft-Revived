package codes.matthewp.sukr.net.packet.update;

import codes.matthewp.sukr.util.ClientUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class WorkerSetS2CPacket {

    public WorkerSetS2CPacket() {
    }

    public WorkerSetS2CPacket(FriendlyByteBuf buf) {
    }

    public void toBytes(FriendlyByteBuf buf) {
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(ClientUtil::closeCurrentUI);
        return true;
    }
}
