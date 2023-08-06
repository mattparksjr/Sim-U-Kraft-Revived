package codes.matthewp.sukr.net.packet.sync;

import codes.matthewp.sukr.data.player.faction.Faction;
import codes.matthewp.sukr.util.ClientUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.HashMap;
import java.util.UUID;
import java.util.function.Supplier;

public class SyncAvailableBuildersS2CPacket {

    private Faction faction;
    private HashMap<UUID, String> map;
    private BlockPos pos;

    public SyncAvailableBuildersS2CPacket(BlockPos pos, Faction faction) {
        this.faction = faction;
        this.pos = pos;
    }

    public SyncAvailableBuildersS2CPacket(FriendlyByteBuf buf) {
        map = Faction.readIDToNameFromBuf(buf);
        pos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf) {
        faction.writeFolkNameToBuf(buf);
        buf.writeBlockPos(pos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ClientUtil.showHire(pos, map);
        });
        return true;
    }
}
