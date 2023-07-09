package codes.matthewp.sukr.event;

import codes.matthewp.sukr.SimUKraft;
import codes.matthewp.sukr.data.structure.StructureData;
import codes.matthewp.sukr.data.structure.StructureManager;
import net.minecraft.server.MinecraftServer;

public class TickHandler {

    public static void handleTick(MinecraftServer server) {
      //  SimUKraft.LOGGER.debug(new StructureManager().getBuildingBlocks(StructureData.getStructures().get(0), server.overworld()).get(0).getState().getBlock().getName().toString());
    }
}
