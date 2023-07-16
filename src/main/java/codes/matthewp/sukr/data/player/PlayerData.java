package codes.matthewp.sukr.data.player;

import codes.matthewp.sukr.SimUKraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

import java.util.UUID;

@AutoRegisterCapability
public class PlayerData {

    private double money;
    private UUID factionID;

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

    public UUID getFactionID() {
        return factionID;
    }

    public void setFactionID(UUID factionID) {
        this.factionID = factionID;
    }

    public void saveNBT(CompoundTag tag) {
        tag.putDouble("money", money);
        if (factionID != null) { // Edge case: world save called before init
            tag.putUUID("factionid", getFactionID());
        }
    }

    public void loadNBT(CompoundTag tag) {
        money = tag.getDouble("money");
        if(tag.get("factionid") != null) {
            setFactionID(tag.getUUID("factionid"));
        }
    }

    public void copyFrom(PlayerData source) {
        this.money = source.money;
    }

}
