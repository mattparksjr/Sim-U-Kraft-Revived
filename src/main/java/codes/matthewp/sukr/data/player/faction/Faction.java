package codes.matthewp.sukr.data.player.faction;

import codes.matthewp.sukr.SimUKraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
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
     * Check if any player in the faction is online :)
     *
     * @param level The level to check
     * @return true if any member of the faction is online
     */
    public boolean isOnline(ServerLevel level) {
        boolean found = false;
        for (UUID uuid : getPlayers()) {
            if (level.getEntity(uuid) != null) {
                found = true;
            }
        }

        if (level.getEntity(getFactionOwner()) != null) {
            found = true;
        }

        return found;
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

    public @NotNull CompoundTag save(CompoundTag tag) {
        tag.putString("name", getName());
        tag.putUUID("uuid", getFactionID());
        tag.putUUID("owner", getFactionOwner());
        for (int i = 0; i < getPlayers().size(); i++) {
            tag.putUUID("member." + i, getPlayers().get(i));
        }
        tag = getData().save(tag);
        return tag;
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
