package codes.matthewp.sukr.data;

public class SimData {

    private int gamemode;

    public SimData(int gamemode) {
        setGamemode(gamemode);
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
