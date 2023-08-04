package codes.matthewp.sukr.net.packet.sync;

import codes.matthewp.sukr.data.player.faction.Faction;
import codes.matthewp.sukr.util.ClientUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.HashMap;
import java.util.UUID;
import java.util.function.Supplier;

public class SyncAvailableBuildersS2CPacket {

    private Faction faction;
    private HashMap<UUID, String> map;

    public SyncAvailableBuildersS2CPacket(Faction faction) {
        this.faction = faction;
    }

    public SyncAvailableBuildersS2CPacket(FriendlyByteBuf buf) {
        map = Faction.readIDToNameFromBuf(buf);
    }

    public void toBytes(FriendlyByteBuf buf) {
        faction.writeFolkNameToBuf(buf);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ClientUtil.openHireBuilderGUI(map);
        });
        return true;
    }
}
