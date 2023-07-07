package codes.matthewp.sukr.data.folk;

import codes.matthewp.sukr.data.SimDataManager;
import net.minecraft.core.BlockPos;

import java.util.Random;

public class FolkData {

    public String firstName;

    public String lastName;
    public int age;

    public int gender;

    public int skinID;

    public int foodLevel;

    public float skillBuild;

    public float skillMine;

    public float skillSoldier;

    public boolean standStill;

    public FolkData() {

    }

    public static FolkData generateRandomFolk() {
        FolkData data = new FolkData();
        Random random = new Random();

        // TODO: SKIN after more are added.
        data.gender = random.nextInt(2);
        if(data.gender == 0) {
            data.firstName = FolkNameData.getFemaleNames().get(random.nextInt(FolkNameData.getFemaleNames().size()));
            data.skinID = 22;
        } else {
            data.firstName = FolkNameData.getMaleNames().get(random.nextInt(FolkNameData.getMaleNames().size()));
            data.skinID = 1;
        }

        data.lastName = FolkNameData.getLastNames().get(random.nextInt(FolkNameData.getLastNames().size()));
        data.age = 18;
        data.foodLevel = 10;
        data.skillBuild = 1f;
        data.skillMine = 1f;
        data.skillSoldier = 1f;
        data.standStill = false;
        return data;
    }

}
