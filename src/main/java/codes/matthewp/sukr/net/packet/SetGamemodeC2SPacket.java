package codes.matthewp.sukr.net.packet;

import codes.matthewp.sukr.data.SimDataManager;
import codes.matthewp.sukr.init.ItemInit;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
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

            for (ServerPlayer player : context.getSender().getServer().overworld().getPlayers(LivingEntity::isAlive)) {
                int location = -1;

                for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                    if (player.getInventory().getItem(i).is(ItemInit.ITEM_GAMEMODE.get())) {
                        location = i;
                    }
                }
                if (location != -1) {
                    player.getInventory().removeItem(location, 1);
                }
            }
        });
        return true;
    }
}
