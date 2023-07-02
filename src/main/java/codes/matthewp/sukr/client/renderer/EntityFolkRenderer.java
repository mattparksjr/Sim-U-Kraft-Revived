package codes.matthewp.sukr.client.renderer;

import codes.matthewp.sukr.SimUKraft;
import codes.matthewp.sukr.client.models.FolkModel;
import codes.matthewp.sukr.entity.EntityFolk;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Pig;
import net.minecraftforge.client.ForgeHooksClient;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

public class EntityFolkRenderer extends MobRenderer<EntityFolk, FolkModel> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(SimUKraft.MODID, "textures/entity/male1.png");
    public EntityFolkRenderer(EntityRendererProvider.Context context) {
        super(context, new FolkModel(context.bakeLayer(FolkModel.LAYER_LOCATION)), 0.7F);
    }

    @Override
    protected void renderNameTag(@NotNull EntityFolk folk, @NotNull Component component, @NotNull PoseStack pose, @NotNull MultiBufferSource buf, int p_114502_) {
        double d0 = this.entityRenderDispatcher.distanceToSqr(folk);
        if (ForgeHooksClient.isNameplateInRenderDistance(folk, d0)) {
            boolean flag = !folk.isDiscrete();
            float f = folk.getNameTagOffsetY();
            pose.pushPose();
            pose.translate(0.0F, f, 0.0F);
            pose.mulPose(this.entityRenderDispatcher.cameraOrientation());
            pose.scale(-0.025F, -0.025F, 0.025F);
            Matrix4f matrix4f = pose.last().pose();
            float f1 = Minecraft.getInstance().options.getBackgroundOpacity(0.25F);
            int j = (int) (f1 * 255.0F) << 24;
            Font font = this.getFont();
            float f2 = (float) (-font.width(component) / 2);
            font.drawInBatch(component, f2, 0, 553648127, false, matrix4f, buf, flag ? Font.DisplayMode.SEE_THROUGH : Font.DisplayMode.NORMAL, j, p_114502_);
            font.drawInBatch(component, f2, 0, 553648127, false, matrix4f, buf, flag ? Font.DisplayMode.SEE_THROUGH : Font.DisplayMode.NORMAL, j, 1);
            font.drawInBatch(component, f2, 0, 553648127, false, matrix4f, buf, flag ? Font.DisplayMode.SEE_THROUGH : Font.DisplayMode.NORMAL, j, 10);
            font.drawInBatch(component, f2, 0, 553648127, false, matrix4f, buf, flag ? Font.DisplayMode.SEE_THROUGH : Font.DisplayMode.NORMAL, j, -50);
            font.drawInBatch(component, f2, 0, 553648127, false, matrix4f, buf, flag ? Font.DisplayMode.SEE_THROUGH : Font.DisplayMode.NORMAL, j, 100);
            if (flag) {
                font.drawInBatch(component, f2, 9, -1, false, matrix4f, buf, Font.DisplayMode.NORMAL, 0, p_114502_);
            }

            pose.popPose();
        }
    }

    @Override
    public ResourceLocation getTextureLocation(EntityFolk entityFolk) {
        return TEXTURE;
    }
}
