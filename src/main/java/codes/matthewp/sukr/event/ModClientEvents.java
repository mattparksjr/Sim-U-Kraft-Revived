package codes.matthewp.sukr.event;

import codes.matthewp.sukr.SimUKraft;
import codes.matthewp.sukr.client.models.FolkModel;
import codes.matthewp.sukr.client.renderer.EntityFolkRenderer;
import codes.matthewp.sukr.init.EntityInit;
import codes.matthewp.sukr.util.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SimUKraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event) {
        event.register(KeyBinding.DEBUG_KEY);
    }

    @SubscribeEvent
    public static void onEntityRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityInit.FOLK.get(), EntityFolkRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterLayerDef(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(FolkModel.LAYER_LOCATION, FolkModel::createBodyLayer);
    }
}
