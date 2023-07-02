package codes.matthewp.sukr.data;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class ClientSimData {

    private static int gamemode;

    public static int getGamemode() {
        return gamemode;
    }

    public static void setGamemode(int gamemode, boolean annonce) {
        ClientSimData.gamemode = gamemode;
        if(!annonce) return;
        if(gamemode == -1) return;
        if(Minecraft.getInstance().player == null) return;

        if (gamemode != 0) {
            Minecraft.getInstance().player.sendSystemMessage(Component.translatable("simukraftr.message.gamemodeset").append(Component.translatable("simukraftr.message.survival")));
        } else {
            Minecraft.getInstance().player.sendSystemMessage(Component.translatable("simukraftr.message.dnrset"));
        }
    }

}
