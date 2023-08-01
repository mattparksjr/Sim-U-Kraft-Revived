package codes.matthewp.sukr.data.player.faction;

import codes.matthewp.sukr.SimUKraft;
import codes.matthewp.sukr.entity.EntityFolk;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.server.ServerLifecycleHooks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * A faction is just a group of players, who share a group of folks.
 * This is used to create different teams when within a multiplayer environment.
 * All the data related to the sims, and the "town" are stored in the FactionSimData.
 */
public class Faction {

    private String name;

    private List<UUID> players;

    private UUID factionID;

    private UUID factionOwner;

    private FactionSimData data;

    public Faction(UUID player) {
      this("System", player);
    }

    public Faction(String name, UUID player) {
        setName(name);
        addPlayer(player);
        setFactionID(UUID.randomUUID());
        setFactionOwner(player);
        setData(new FactionSimData());
    }

    public Faction(CompoundTag tag) {
        load(tag);
    }

    public ServerPlayer getFirstOnline(ServerLevel level) {
        if (level.getEntity(getFactionOwner()) != null) {
            return (ServerPlayer) level.getEntity(getFactionOwner());
        }
        for (UUID uuid : getPlayers()) {
            if (level.getEntity(uuid) != null) {
                return (ServerPlayer) level.getEntity(uuid);
            }
        }
        SimUKraft.LOGGER.debug("FATAL: Returning null for getFirstOnline()");
        return null;
    }

    /**
     * Get a list of all players who are currently online
     * @param level ServerLevel the world
     * @return List of online players
     */
    public List<ServerPlayer> getOnlinePlayers(ServerLevel level) {
        List<ServerPlayer> online = new ArrayList<>();

        if(isPlayerOnline(level, getFactionOwner()))
            online.add((ServerPlayer) level.getEntity(getFactionOwner()));

        for(UUID uuid : getPlayers()) {
            if((uuid != getFactionOwner()) && isPlayerOnline(level, uuid))
                online.add((ServerPlayer) level.getEntity(uuid));
        }
        return online;
    }

    /**
     * Check if any player in the faction is online :)
     *
     * @param level The level to check
     * @return true if any member of the faction is online
     */
    public boolean isOnline(ServerLevel level) {
        boolean found = false;
        for (UUID uuid : getPlayers()) {
            if (isPlayerOnline(level, uuid)) {
                found = true;
            }
        }

        if (isPlayerOnline(level, getFactionOwner())) {
            found = true;
        }

        return found;
    }

    /**
     * Check if a specific player is online.
     * @param level ServerLevel servers level
     * @param uuid UUID players uuid
     * @return true if player is online
     */
    public boolean isPlayerOnline(ServerLevel level, UUID uuid) {
        return level.getEntity(uuid) != null;
    }

    public void load(CompoundTag tag) {
        setName(tag.getString("name"));
        setFactionID(tag.getUUID("uuid"));
        setFactionOwner(tag.getUUID("owner"));
        List<UUID> members = new ArrayList<>();
        for (int i = 0; i < tag.getInt("membersize"); i++) {
            members.add(tag.getUUID("member." + i));
        }
        setPlayers(members);
        setData(new FactionSimData().load(tag));
    }

    public void save(CompoundTag tag) {
        tag.putString("name", getName());
        tag.putUUID("uuid", getFactionID());
        tag.putUUID("owner", getFactionOwner());
        tag.putInt("membersize", getPlayers().size());
        for (int i = 0; i < getPlayers().size(); i++) {
            tag.putUUID("member." + i, getPlayers().get(i));
        }
        tag.putInt("numfolks", getData().getFolks().size());
        for (int i = 0; i < getData().getFolks().size(); i++) {
            tag.putUUID("folks." + i, getData().getFolks().get(i));
        }
    }


    public void writeFolkNameToBuf(FriendlyByteBuf buf) {
        // TODO: 4096 byte limit (27 folk limit approx.)
        buf.writeInt(getData().getFolks().size());
        for (int i = 0; i < getData().getFolks().size(); i++) {
            UUID uuid = getData().getFolks().get(i);
            if(ServerLifecycleHooks.getCurrentServer() != null) {
                EntityFolk folk = (EntityFolk) ServerLifecycleHooks.getCurrentServer().overworld().getEntity(uuid);
                buf.writeUUID(uuid);
                buf.writeUtf(folk.getFullname());
            }
        }
    }

    public static HashMap<UUID, String> readIDToNameFromBuf(FriendlyByteBuf buf) {
        HashMap<UUID, String> map = new HashMap<>();
        int total = buf.readInt();
        for (int i = 0; i < total; i++) {
            UUID uuid = buf.readUUID();
            String name = buf.readUtf();
            map.put(uuid, name);
        }
        return map;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPlayers(List<UUID> players) {
        this.players = players;
    }

    public List<UUID> getPlayers() {
        return players;
    }

    public void addPlayer(UUID uuid) {
        if (this.players == null) {
            players = new ArrayList<>();
        }
        this.players.add(uuid);
    }

    public UUID getFactionID() {
        return factionID;
    }

    private void setFactionID(UUID factionID) {
        this.factionID = factionID;
    }

    public FactionSimData getData() {
        return data;
    }

    public void setData(FactionSimData data) {
        this.data = data;
    }

    public void setFactionOwner(UUID factionOwner) {
        this.factionOwner = factionOwner;
    }

    public UUID getFactionOwner() {
        return factionOwner;
    }
}
