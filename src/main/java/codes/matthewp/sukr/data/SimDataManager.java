package codes.matthewp.sukr.data;

import codes.matthewp.sukr.entity.EntityFolk;
import codes.matthewp.sukr.net.PacketHandler;
import codes.matthewp.sukr.net.packet.FolkSpawnedS2CPacket;
import codes.matthewp.sukr.net.packet.SyncGamemodeS2CPacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.UUID;

public class SimDataManager extends SavedData {

    private final SimData data = new SimData();

    public SimDataManager() {

    }

    public SimDataManager(@NotNull CompoundTag tag) {
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

    public void addFolk(EntityFolk folk) {
        data.getFolks().add(folk.getUUID());
        setDirty();
        PacketHandler.sendToAllPlayers(new FolkSpawnedS2CPacket(folk.getId()));
    }

    public SimData getData() {
        return data;
    }

    @Override
    public @NotNull CompoundTag save(CompoundTag tag) {
        tag.putInt("gamemode", data.getGamemode());
        return tag;
    }
}
