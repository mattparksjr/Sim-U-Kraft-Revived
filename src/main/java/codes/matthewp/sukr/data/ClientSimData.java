package codes.matthewp.sukr.data;

import codes.matthewp.sukr.SimUKraft;
import codes.matthewp.sukr.data.player.faction.Faction;
import codes.matthewp.sukr.entity.EntityFolk;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClientSimData {

    private static int gamemode;
    private static Faction faction;

    public static int getGamemode() {
        return gamemode;
    }

    public static void setGamemode(int gamemode, boolean annonce) {
        ClientSimData.gamemode = gamemode;
        if (!annonce) return;
        if (gamemode == -1) return;
        if (Minecraft.getInstance().player == null) return;

        if (gamemode != 0) {
            Minecraft.getInstance().player.sendSystemMessage(Component.translatable("simukraftr.message.gamemodeset").append(Component.translatable("simukraftr.message.survival")));
        } else {
            Minecraft.getInstance().player.sendSystemMessage(Component.translatable("simukraftr.message.dnrset"));
        }
    }

    public static void addSim(int id) {
        EntityFolk folk = (EntityFolk) Minecraft.getInstance().level.getEntity(id);
        faction.getData().getFolks().add(folk.getUUID());
        Minecraft.getInstance().player.sendSystemMessage(Component.literal(I18n.get("simukraftr.message.folkspawned", folk.getFullname())));
    }

    public static Faction getFaction() {
        return faction;
    }

    public static void setFaction(Faction newFaction) {
        SimUKraft.LOGGER.debug("CLIENT - WE HAVE BEEN TOLD OUR FACTION IS: "  +newFaction.getFactionID() + " ---- " + newFaction.getFactionOwner());
        ClientSimData.faction = newFaction;
    }
}
