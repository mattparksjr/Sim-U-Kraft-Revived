package codes.matthewp.sukr.event;

import codes.matthewp.sukr.SimUKraft;
import codes.matthewp.sukr.entity.EntityFolk;
import codes.matthewp.sukr.init.EntityInit;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SimUKraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCommonEvents {

    @SubscribeEvent
    public static void onAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityInit.FOLK.get(), EntityFolk.getFolkAttributes().build());
    }
}
