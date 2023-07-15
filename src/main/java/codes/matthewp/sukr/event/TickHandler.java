package codes.matthewp.sukr.event;

import codes.matthewp.sukr.SimUKraft;
import codes.matthewp.sukr.data.SimDataManager;
import codes.matthewp.sukr.entity.EntityFolk;
import codes.matthewp.sukr.init.EntityInit;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.Random;

public class TickHandler {

    public static void handleTick(MinecraftServer server) {
        if (SimDataManager.get(server.overworld()).getGamemode() == 0 || SimDataManager.get(server.overworld()).getGamemode() == -1) {
            return;
        }

        // TODO: shouldSpawnNewFolk()
        if (SimDataManager.get(server.overworld()).getData().getFolks().size() == 0) {
            summonNewFolk(server);
        }

        //  SimUKraft.LOGGER.debug(new StructureManager().getBuildingBlocks(StructureData.getStructures().get(0), server.overworld()).get(0).getState().getBlock().getName().toString());
    }

    private static void summonNewFolk(MinecraftServer server) {
        Random random = new Random();
        if(server.overworld().players().isEmpty()) return;
        // TODO: This will be based on teams
        ServerPlayer player = server.overworld().players().get(random.nextInt(server.overworld().players().size()));
        int x = (random.nextInt(2) == 1) ? player.getBlockX() + random.nextInt(25) : player.getBlockX() - random.nextInt(25);
        int z = (random.nextInt(2) == 1) ? player.getBlockZ() + random.nextInt(25) : player.getBlockZ() - random.nextInt(25);
        BlockPos pos = server.overworld().getHeightmapPos(Heightmap.Types.WORLD_SURFACE, new BlockPos(x, 0, z));

        EntityFolk folk = EntityInit.FOLK.get().spawn(server.overworld(), pos, MobSpawnType.NATURAL);
        SimDataManager.get(server.overworld()).addFolk(folk);
    }
}
