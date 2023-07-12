package codes.matthewp.sukr.event;

import codes.matthewp.sukr.SimUKraft;
import codes.matthewp.sukr.data.SimDataManager;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;

public class TickHandler {

    public static void handleTick(MinecraftServer server) {
        if(SimDataManager.get(server.overworld()).getGamemode() == 0) {
            return;
        }

        //  SimUKraft.LOGGER.debug(new StructureManager().getBuildingBlocks(StructureData.getStructures().get(0), server.overworld()).get(0).getState().getBlock().getName().toString());
    }
}
