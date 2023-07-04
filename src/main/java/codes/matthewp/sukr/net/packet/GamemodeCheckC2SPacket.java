package codes.matthewp.sukr.net.packet;

import codes.matthewp.sukr.data.SimDataManager;
import codes.matthewp.sukr.net.PacketHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class GamemodeCheckC2SPacket {

    public GamemodeCheckC2SPacket() {

    }

    public GamemodeCheckC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ServerLevel level = player.serverLevel();
            PacketHandler.sendToPlayer(new SyncGamemodeS2CPacket(SimDataManager.get(level).getGamemode(), false), player);
        });
        return true;
    }
}
