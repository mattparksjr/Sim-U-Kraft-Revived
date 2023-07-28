package codes.matthewp.sukr.data;

import codes.matthewp.sukr.SimUKraft;
import codes.matthewp.sukr.data.folk.FolkData;
import codes.matthewp.sukr.data.player.faction.Faction;
import codes.matthewp.sukr.data.player.faction.FactionSimData;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SimData {

    private int gamemode;
    private List<Faction> factions;

    public SimData(int gamemode) {
        setGamemode(gamemode);
        factions = new ArrayList<>();
    }

    public SimData() {
        setGamemode(-1);
        factions = new ArrayList<>();
    }

    public int getGamemode() {
        return gamemode;
    }

    public void setGamemode(int gamemode) {
        this.gamemode = gamemode;
    }

    public List<Faction> getFactions() {
        return factions;
    }

    public Faction getPlayerFaction(UUID uuid) {
        // TODO: This is _prob_ fine, but its 100% a bad way to do this...
        for(Faction faction : factions) {
            if(faction.getFactionOwner().equals(uuid)) {
                return faction;
            }
            if(faction.getPlayers().contains(uuid)) {
                return faction;
            }
        }
        SimUKraft.LOGGER.error("FATAL! Tried to get a faction, of a player who is not in a valid one!");
        return null;
    }

    public void setFactions(List<Faction> factions) {
        this.factions = factions;
    }

    public void addFaction(Faction faction) {
        this.factions.add(faction);
    }
}
