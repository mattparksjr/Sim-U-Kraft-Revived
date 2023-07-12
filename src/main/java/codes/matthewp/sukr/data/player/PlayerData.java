package codes.matthewp.sukr.data.player;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class PlayerData {

    private double money;

    public PlayerData() {
        // TODO: Config value
        money = 20.0D;
    }

    public double getMoney() {
        return money;
    }

    public void addMoney(double amount) {
        this.money = this.money + amount;
    }

    public void subMoney(double amount) {
        this.money = this.money - amount;
    }

    public void saveNBT(CompoundTag tag) {
        tag.putDouble("money", money);
    }

    public void loadNBT(CompoundTag tag) {
        money = tag.getDouble("money");
    }

    public void copyFrom(PlayerData source) {
        this.money = source.money;
    }

}
