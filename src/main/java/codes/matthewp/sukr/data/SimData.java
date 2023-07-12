package codes.matthewp.sukr.data;

import codes.matthewp.sukr.data.folk.FolkData;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SimData {

    private int gamemode;
    private List<UUID> sims;

    public SimData(int gamemode) {
        setGamemode(gamemode);
        sims = new ArrayList<>();
    }

    public SimData() {
        setGamemode(-1);
    }

    public int getGamemode() {
        return gamemode;
    }

    public void setGamemode(int gamemode) {
        this.gamemode = gamemode;
    }

    public List<UUID> getFolks() {
        return sims;
    }
}
