package codes.matthewp.sukr.data;

import codes.matthewp.sukr.SimUKraft;
import codes.matthewp.sukr.data.player.faction.Faction;
import codes.matthewp.sukr.entity.EntityFolk;
import codes.matthewp.sukr.net.PacketHandler;
import codes.matthewp.sukr.net.packet.FactionAddedS2CPacket;
import codes.matthewp.sukr.net.packet.FolkSpawnedS2CPacket;
import codes.matthewp.sukr.net.packet.SyncGamemodeS2CPacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SimDataManager extends SavedData {

    private final SimData data = new SimData();

    public SimDataManager() {

    }

    public SimDataManager(@NotNull CompoundTag tag) {
        load(tag);
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

    public void addFolk(ServerLevel level, Faction faction, EntityFolk folk) {
        faction.getData().addFolk(folk);
        setDirty();
        for(ServerPlayer player : faction.getOnlinePlayers(level)) {
            PacketHandler.sendToPlayer(new FolkSpawnedS2CPacket(folk.getId()), player);
        }
    }

    public void addFaction(Faction faction, ServerPlayer player) {
        SimUKraft.LOGGER.debug("SERVER - ADDED FACTION, SENDING TO PLAYER");
        data.addFaction(faction);
        setDirty();
        PacketHandler.sendToPlayer(new FactionAddedS2CPacket(faction), player);
    }

    public SimData getData() {
        return data;
    }

    @Override
    public @NotNull CompoundTag save(CompoundTag tag) {
        tag.putInt("gamemode", data.getGamemode());
        tag.putInt("numfactions", data.getFactions().size());
        for (int i = 0; i < data.getFactions().size(); i++) {
            Faction faction = data.getFactions().get(i);
            faction.save(tag.getCompound("faction." + i));
        }
        return tag;
    }

    public void load(CompoundTag tag) {
        data.setGamemode(tag.getInt("gamemode"));
        List<Faction> factions = new ArrayList<>();
        for (int i = 0; i < tag.getInt("numfactions"); i++) {
            Faction faction = new Faction(tag.getCompound("faction." + i));
            factions.add(faction);
        }
        data.setFactions(factions);
    }
}
