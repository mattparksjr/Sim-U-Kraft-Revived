package codes.matthewp.sukr.net.packet;

import codes.matthewp.sukr.data.ClientSimData;
import codes.matthewp.sukr.data.player.faction.Faction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class FactionAddedS2CPacket {

    private final Faction faction;

    public FactionAddedS2CPacket(Faction faction) {
        this.faction = faction;
    }

    public FactionAddedS2CPacket(FriendlyByteBuf buf) {
        this.faction = new Faction(buf.readUtf(), buf.readUUID());
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(faction.getName());
        buf.writeUUID(faction.getFactionOwner());
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ClientSimData.setFaction(faction);
        });
        return true;
    }
}
