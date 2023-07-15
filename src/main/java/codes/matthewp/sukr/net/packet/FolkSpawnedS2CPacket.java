package codes.matthewp.sukr.net.packet;

import codes.matthewp.sukr.data.ClientSimData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class FolkSpawnedS2CPacket {

    private final int folk;

    public FolkSpawnedS2CPacket(int uuid) {
        this.folk = uuid;
    }

    public FolkSpawnedS2CPacket(FriendlyByteBuf buf) {
        this.folk = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(folk);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ClientSimData.addSim(folk);
        });
        return true;
    }
}
