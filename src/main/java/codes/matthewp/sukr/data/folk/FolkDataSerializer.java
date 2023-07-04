package codes.matthewp.sukr.data.folk;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;

public class FolkDataSerializer implements EntityDataSerializer {


    @Override
    public void write(FriendlyByteBuf p_135025_, Object p_135026_) {

    }

    @Override
    public Object read(FriendlyByteBuf p_135024_) {
        return null;
    }

    @Override
    public EntityDataAccessor createAccessor(int p_135022_) {
        return EntityDataSerializer.super.createAccessor(p_135022_);
    }

    @Override
    public Object copy(Object p_135023_) {
        return null;
    }
}
