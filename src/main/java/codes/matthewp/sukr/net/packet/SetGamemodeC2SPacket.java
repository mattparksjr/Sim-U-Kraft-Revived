package codes.matthewp.sukr.net.packet;

import codes.matthewp.sukr.data.SimDataManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SetGamemodeC2SPacket {

    private final int gamemode;

    public SetGamemodeC2SPacket(int gamemode) {
        this.gamemode = gamemode;
    }

    public SetGamemodeC2SPacket(FriendlyByteBuf buf) {
        this.gamemode = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(gamemode);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            SimDataManager.get(context.getSender().getServer().overworld()).setGamemode(gamemode, true);
        });
        return true;
    }
}
