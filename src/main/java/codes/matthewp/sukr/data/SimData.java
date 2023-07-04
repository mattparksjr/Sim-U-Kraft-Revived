package codes.matthewp.sukr.data;

import java.util.ArrayList;
import java.util.UUID;

public class SimData {

    private int gamemode;
    private ArrayList<UUID> sims;

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
}
