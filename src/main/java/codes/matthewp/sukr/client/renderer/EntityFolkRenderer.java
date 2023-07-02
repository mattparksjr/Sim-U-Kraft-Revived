package codes.matthewp.sukr.client.renderer;

import codes.matthewp.sukr.SimUKraft;
import codes.matthewp.sukr.client.models.FolkModel;
import codes.matthewp.sukr.entity.EntityFolk;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Pig;

public class EntityFolkRenderer extends MobRenderer<EntityFolk, FolkModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(SimUKraft.MODID, "textures/entity/male1.png");
    public EntityFolkRenderer(EntityRendererProvider.Context context) {
        super(context, new FolkModel(context.bakeLayer(FolkModel.LAYER_LOCATION)), 0.7F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityFolk entityFolk) {
        return TEXTURE;
    }
}
