package codes.matthewp.sukr.data.player.faction;

import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * All the data related to the "town" for each faction is managed here.
 */
public class FactionSimData {

    private List<UUID> folks;

    public FactionSimData() {
        folks = new ArrayList<>();
    }

    public CompoundTag save(@NotNull CompoundTag tag) {
        tag.putInt("numfolks", getFolks().size());
        for (int i = 0; i < getFolks().size(); i++) {
            tag.putUUID("folks." + i, getFolks().get(i));
        }
        return tag;
    }

    public FactionSimData load(CompoundTag tag) {
        List<UUID> folks = new ArrayList<>();
        for (int i = 0; i < tag.getInt("numfolks"); i++) {
            folks.add(tag.getUUID("folks." + i));
        }
        setFolks(folks);
        return this;
    }

    public List<UUID> getFolks() {
        return folks;
    }

    public void setFolks(List<UUID> folks) {
        this.folks = folks;
    }

    public void addFolk(UUID uuid) {
        folks.add(uuid);
    }
}
