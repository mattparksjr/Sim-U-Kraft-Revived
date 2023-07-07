package codes.matthewp.sukr.data;

import codes.matthewp.sukr.net.PacketHandler;
import codes.matthewp.sukr.net.packet.SyncGamemodeS2CPacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;

import javax.annotation.Nonnull;

public class
SimDataManager extends SavedData {

    private final SimData data = new SimData();

    public SimDataManager() {

    }

    public SimDataManager(CompoundTag tag) {
        data.setGamemode(tag.getInt("gamemode"));
    }

    @Nonnull
    public static SimDataManager get(ServerLevel level) {
        if (level.isClientSide) {
            throw new RuntimeException("Illegal attempt to access server data from client side!");
        }

        DimensionDataStorage storage = level.getDataStorage();
        return storage.computeIfAbsent(SimDataManager::new, SimDataManager::new, "simdatamanager");
    }


    public int getGamemode() {
        return data.getGamemode();
    }

    public void setGamemode(int gamemode, boolean announce) {
        data.setGamemode(gamemode);
        setDirty();
        PacketHandler.sendToAllPlayers(new SyncGamemodeS2CPacket(gamemode, announce));
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.putInt("gamemode", data.getGamemode());
        return tag;
    }
}
