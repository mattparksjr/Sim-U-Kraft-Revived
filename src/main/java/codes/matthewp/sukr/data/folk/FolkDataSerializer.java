package codes.matthewp.sukr.data.folk;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.syncher.EntityDataSerializer;

public class FolkDataSerializer<FolkData> implements EntityDataSerializer<FolkData> {

    @Override
    public void write(FriendlyByteBuf buf, Object obj) {
    }

    @Override
    public FolkData read(FriendlyByteBuf buf) {
        return null;
    }

    @Override
    public Object copy(Object p_135023_) {
        return null;
    }
}
