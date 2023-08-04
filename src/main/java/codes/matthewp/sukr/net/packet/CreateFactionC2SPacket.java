package codes.matthewp.sukr.net.packet;

import codes.matthewp.sukr.data.SimDataManager;
import codes.matthewp.sukr.data.player.PlayerDataProvider;
import codes.matthewp.sukr.data.player.faction.Faction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CreateFactionC2SPacket {

    private final Faction faction;

    public CreateFactionC2SPacket(Faction faction) {
        this.faction = faction;
    }

    public CreateFactionC2SPacket(FriendlyByteBuf buf) {
        this.faction = new Faction(buf.readUtf(), buf.readUUID());
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(faction.getName());
        buf.writeUUID(faction.getFactionOwner());
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            context.getSender().getCapability(PlayerDataProvider.PLAYER_DATA).ifPresent(playerData -> {
                playerData.setFactionID(faction.getFactionID());
            });
            SimDataManager.get(context.getSender().getServer().overworld()).addFaction(faction, context.getSender());
        });
        return true;
    }
}
