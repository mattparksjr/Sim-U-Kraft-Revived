package codes.matthewp.sukr.net.packet.sync;

import codes.matthewp.sukr.SimUKraft;
import codes.matthewp.sukr.data.SimDataManager;
import codes.matthewp.sukr.data.structure.*;
import codes.matthewp.sukr.net.PacketHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class RequestStructureListC2SPacket {

    private BlockPos pos;
    private StructureCategory category;

    public RequestStructureListC2SPacket(BlockPos pos, StructureCategory category) {
        this.pos = pos;
        this.category = category;
    }

    public RequestStructureListC2SPacket(FriendlyByteBuf buf) {
        pos = buf.readBlockPos();
        category = buf.readEnum(StructureCategory.class);
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
        buf.writeEnum(category);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            List<Structure> structures = StructureData.getStructuresByCategory(category);
            List<StructurePacketData> structurePacketData = new ArrayList<>();

            for(Structure structure : structures) {
                structurePacketData.add(new StructurePacketData(
                        structure.getId(),
                        structure.getName(),
                        new StructureManager().getSize(structure, context.getSender().serverLevel()),
                        new StructureManager().getBuildingBlocks(structure, context.getSender().serverLevel()).size() * 0.02d, structure.getAuthor()));
            }

            PacketHandler.sendToPlayer(new SyncStructuresS2CPacket(pos, category, structurePacketData), context.getSender());
        });
        return true;
    }
}
