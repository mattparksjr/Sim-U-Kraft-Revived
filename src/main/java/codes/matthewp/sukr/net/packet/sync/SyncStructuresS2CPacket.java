package codes.matthewp.sukr.net.packet.sync;

import codes.matthewp.sukr.SimUKraft;
import codes.matthewp.sukr.data.structure.StructureCategory;
import codes.matthewp.sukr.data.structure.StructurePacketData;
import codes.matthewp.sukr.util.ClientUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class SyncStructuresS2CPacket {

    private BlockPos pos;
    private List<StructurePacketData> structures;
    private StructureCategory category;

    public SyncStructuresS2CPacket(BlockPos pos, StructureCategory category, List<StructurePacketData> structures) {
        this.pos = pos;
        this.category = category;
        this.structures = structures;
    }

    public SyncStructuresS2CPacket(FriendlyByteBuf buf) {
        pos = buf.readBlockPos();
        category = buf.readEnum(StructureCategory.class);
        List<StructurePacketData> data = new ArrayList<>();
        int amount = buf.readInt();

        for (int i = 0; i < amount; i++) {
            data.add(new StructurePacketData(buf.readUtf(), buf.readUtf(), new Vec3i(buf.readInt(), buf.readInt(), buf.readInt()), buf.readDouble(), buf.readUtf()));
        }
        structures = data;
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
        buf.writeEnum(category);
        buf.writeInt(structures.size());
        for (StructurePacketData data : structures) {
            buf.writeUtf(data.getId());
            buf.writeUtf(data.getName());
            buf.writeInt(data.getSize().getX());
            buf.writeInt(data.getSize().getY());
            buf.writeInt(data.getSize().getZ());
            buf.writeDouble(data.getCost());
            buf.writeUtf(data.getAuthor());
        }
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ClientUtil.showChooseBuilding(pos, structures, category);
        });
        return true;
    }
}
