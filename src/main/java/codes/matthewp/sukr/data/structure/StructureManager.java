package codes.matthewp.sukr.data.structure;

import codes.matthewp.sukr.SimUKraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class StructureManager {

    public ArrayList<StructureBlock> getBuildingBlocks(Structure structure, Level level) {
        return getBuildingBlocks(structure.getFile(), level);
    }

    private ArrayList<StructureBlock> getBuildingBlocks(String structureName, Level level) {
        CompoundTag nbt = getBuildingNbt(structureName);

        if (nbt == null) {
            SimUKraft.LOGGER.debug("Returning an empty list of building blocks, due to an error.");
            return new ArrayList<>();
        }
        ArrayList<StructureBlock> blocks = new ArrayList<>();

        // load in blocks (list of blockPos and their palette index)
        ListTag blocksNbt = nbt.getList("blocks", 10);

        ArrayList<BlockState> palette = getBuildingPalette(nbt, level);

        for (int i = 0; i < blocksNbt.size(); i++) {
            CompoundTag blockNbt = blocksNbt.getCompound(i);
            ListTag blockPosNbt = blockNbt.getList("pos", 3);

            blocks.add(new StructureBlock(
                    new BlockPos(
                            blockPosNbt.getInt(0),
                            blockPosNbt.getInt(1),
                            blockPosNbt.getInt(2)
                    ),
                    palette.get(blockNbt.getInt("state"))
            ));
        }
        return blocks;
    }

    public CompoundTag getBuildingNbt(String structureName) {
        try {
            ResourceLocation rl = new ResourceLocation(SimUKraft.MODID, "structure/" + structureName + ".nbt");
            InputStream rs = this.getClass().getClassLoader().
                    getResourceAsStream("data/" + rl.getNamespace() + "/" + rl.getPath());
            if (rs == null) return null;
            return NbtIo.readCompressed(rs);
        } catch (IOException ex) {
            SimUKraft.LOGGER.error("Error reading building NBT {}, {}", structureName, ex);
        }
        return null;
    }

    public static ArrayList<BlockState> getBuildingPalette(CompoundTag nbt, Level level) {
        ArrayList<BlockState> palette = new ArrayList<>();
        // load in palette (list of unique blockstates)
        ListTag paletteNbt = nbt.getList("palette", 10);
        for (int i = 0; i < paletteNbt.size(); i++)
            palette.add(NbtUtils.readBlockState(level.holderLookup(Registries.BLOCK), paletteNbt.getCompound(i)));
        return palette;
    }
}
