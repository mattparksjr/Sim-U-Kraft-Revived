package codes.matthewp.sukr.data.folk;

import net.minecraft.core.BlockPos;

import java.util.Random;

public class FolkData {

    private BlockPos home;
    private BlockPos jobSite;

    private String name;

    private int age;

    private int gender;

    private int skinID;

    private int foodLevel;

    private int skillBuild;

    private int skillMine;

    private int skillSoldier;

    private boolean standStill;

    public FolkData() {

    }


    public static FolkData generateRandom() {
        FolkData data = new FolkData();
        Random random = new Random();
        data.gender = random.nextInt(2);

        return data;
    }

}
