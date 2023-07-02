package codes.matthewp.sukr.net.packet;

import codes.matthewp.sukr.SimUKraft;
import codes.matthewp.sukr.data.ClientSimData;
import codes.matthewp.sukr.data.SimData;
import codes.matthewp.sukr.data.SimDataManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncGamemodeS2CPacket {

    private final int gamemode;
    private final boolean announce;

    public SyncGamemodeS2CPacket(int gamemode, boolean announce) {
        this.gamemode = gamemode;
        this.announce = announce;
    }

    public SyncGamemodeS2CPacket(FriendlyByteBuf buf) {
        this.gamemode = buf.readInt();
        this.announce = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(gamemode);
        buf.writeBoolean(announce);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ClientSimData.setGamemode(gamemode, announce);
        });
        return true;
    }
}
