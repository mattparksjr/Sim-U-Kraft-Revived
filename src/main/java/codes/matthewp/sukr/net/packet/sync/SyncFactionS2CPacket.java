package codes.matthewp.sukr.net.packet.sync;

import codes.matthewp.sukr.data.ClientSimData;
import codes.matthewp.sukr.data.player.faction.Faction;
import codes.matthewp.sukr.net.PacketHandler;
import codes.matthewp.sukr.net.packet.gui.ShowHireBuilderS2CPacket;
import codes.matthewp.sukr.util.ClientUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.packs.repository.Pack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncFactionS2CPacket {

    private final Faction faction;

    public SyncFactionS2CPacket(Faction faction) {
        this.faction = faction;
    }

    public SyncFactionS2CPacket(FriendlyByteBuf buf) {
        faction = Faction.readFromBuf(buf);
    }

    public void toBytes(FriendlyByteBuf buf) {
        faction.writeToBuf(buf);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ClientSimData.setFaction(faction);
            // TODO: If this packet is going to be used in more places, we need to move this around
            ClientUtil.openHireBuilderGUI();
        });
        return true;
    }

}
