package codes.matthewp.sukr.event;

import codes.matthewp.sukr.SimUKraft;
import codes.matthewp.sukr.data.SimDataManager;
import codes.matthewp.sukr.data.player.faction.Faction;
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
        SimDataManager simData = SimDataManager.get(server.overworld());
        if (simData.getGamemode() == 0 || simData.getGamemode() == -1) {
            return;
        }

        for(Faction faction : simData.getData().getFactions()) {
            if(shouldSpawnNewFolk(server.overworld(), faction)) {
                summonNewFolk(server, faction);
            }
        }
        //  SimUKraft.LOGGER.debug(new StructureManager().getBuildingBlocks(StructureData.getStructures().get(0), server.overworld()).get(0).getState().getBlock().getName().toString());
    }

    private static void summonNewFolk(MinecraftServer server, Faction faction) {
        Random random = new Random();
        ServerLevel level = server.overworld();
        if(level.players().isEmpty()) return;

        ServerPlayer player = faction.getFirstOnline(level);
        int x = (random.nextInt(2) == 1) ? player.getBlockX() + random.nextInt(25) : player.getBlockX() - random.nextInt(25);
        int z = (random.nextInt(2) == 1) ? player.getBlockZ() + random.nextInt(25) : player.getBlockZ() - random.nextInt(25);
        BlockPos pos = level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE, new BlockPos(x, 0, z));

        EntityFolk folk = EntityInit.FOLK.get().spawn(level, pos, MobSpawnType.NATURAL);
        if(folk != null) {
            SimDataManager.get(server.overworld()).addFolk(server.overworld(), faction, folk);
        } else {
            SimUKraft.LOGGER.error("Failed to spawn a new folk at: " + pos.toShortString());
        }
    }

    private static boolean shouldSpawnNewFolk(ServerLevel level, Faction faction) {
        if(!faction.isOnline(level)) return false;
        if(faction.getData().getFolks().isEmpty()) return true;
        return false;
    }
}
