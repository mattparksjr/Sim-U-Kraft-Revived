package codes.matthewp.sukr.net.packet.sync;

import codes.matthewp.sukr.data.ClientSimData;
import codes.matthewp.sukr.data.SimDataManager;
import codes.matthewp.sukr.data.player.faction.Faction;
import codes.matthewp.sukr.net.PacketHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class RequestFactionDataC2SPacket {


    public RequestFactionDataC2SPacket() {
    }

    public RequestFactionDataC2SPacket(FriendlyByteBuf buf) {
    }

    public void toBytes(FriendlyByteBuf buf) {
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            PacketHandler.sendToPlayer(new SyncFactionS2CPacket(SimDataManager.get(context.getSender().serverLevel()).getData().getPlayerFaction(context.getSender().getUUID())), context.getSender());
        });
        return true;
    }
}
