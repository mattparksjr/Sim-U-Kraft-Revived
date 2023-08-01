package codes.matthewp.sukr.data.player.faction;

import codes.matthewp.sukr.SimUKraft;
import codes.matthewp.sukr.entity.EntityFolk;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * All the data related to the "town" for each faction is managed here.
 */
public class FactionSimData {

    private List<UUID> folks;
    //
    private HashMap<Integer, UUID> clientEntityIDs;


    public FactionSimData() {
        folks = new ArrayList<>();
        clientEntityIDs = new HashMap<>();
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

    public HashMap<Integer, UUID> getClientEntityIDs() {
        return clientEntityIDs;
    }

    public void addFolk(EntityFolk folk) {
        folks.add(folk.getUUID());
        clientEntityIDs.put(folk.getId(), folk.getUUID());
    }

    public List<EntityFolk> getUnemployedFolks(ServerLevel level) {
        List<EntityFolk> folks = new ArrayList<>();
        for(UUID uuid : getFolks()) {
            EntityFolk folk = (EntityFolk) level.getEntity(uuid);
            if(folk != null && folk.getEntityData().get(EntityFolk.JOB_SITE).getY() == 999) {
                folks.add(folk);
            }
        }
        return folks;
    }

    public List<EntityFolk> getUnemployedFolks() {
        List<EntityFolk> folks = new ArrayList<>();
        SimUKraft.LOGGER.debug("LOOKING FOR FOLKS....");
        for(Integer id : getClientEntityIDs().keySet()) {
            EntityFolk folk = (EntityFolk) Minecraft.getInstance().level.getEntity(id);
            SimUKraft.LOGGER.debug("LOOKING FOR ID: " + id + " ---- " + (folk == null) +" ---- " + (folk.getEntityData().get(EntityFolk.JOB_SITE).getY() == 999));
            if(folk != null && folk.getEntityData().get(EntityFolk.JOB_SITE).getY() == 999) {
                folks.add(folk);
            }
        }
        return folks;
    }
}
