package codes.matthewp.sukr.client.renderer;

import codes.matthewp.sukr.SimUKraft;
import codes.matthewp.sukr.entity.EntityFolk;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

public class EntityFolkRenderer extends LivingEntityRenderer<EntityFolk, HumanoidModel<EntityFolk>> {

    public EntityFolkRenderer(EntityRendererProvider.Context context) {
        //   super(context, new FolkModel(context.bakeLayer(FolkModel.LAYER_LOCATION)), 0.7F);
        super(context, new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER)), 0.5f);
    }


    @Override
    public void render(EntityFolk entityFolk, float p_115309_, float p_115310_, PoseStack stack, MultiBufferSource bufferSource, int p_115313_) {
        if (entityFolk.getEntityData().get(EntityFolk.AGE) < 18) {
            stack.scale(0.5F, 0.5F, 0.5F);
        }
        super.render(entityFolk, p_115309_, p_115310_, stack, bufferSource, p_115313_);
    }

    @Override
    protected void renderNameTag(@NotNull EntityFolk folk, @NotNull Component component, @NotNull PoseStack pose, @NotNull MultiBufferSource buf, int packedLightCoords) {
        double distanceToSqr = this.entityRenderDispatcher.distanceToSqr(folk);
        float baseOffset = folk.getNameTagOffsetY();
        float opacity = Minecraft.getInstance().options.getBackgroundOpacity(0.25F);
        Font font = this.getFont();

        if (distanceToSqr > 100.0) return;

        // Player is looking directly at folk, need to show detail view.
        if (this.entityRenderDispatcher.crosshairPickEntity != null && this.entityRenderDispatcher.crosshairPickEntity.distanceToSqr(folk) <= 4.0) {
            renderLine(Component.literal(folk.getEntityData().get(EntityFolk.FIRST_NAME) + " " + folk.getEntityData().get(EntityFolk.LAST_NAME) + "(" + folk.getEntityData().get(EntityFolk.AGE) + ")"), pose, baseOffset + 1.25f, opacity, font, buf, packedLightCoords);
            renderLine(Component.literal("Folk current task").withStyle(ChatFormatting.YELLOW), pose, baseOffset + 1f, opacity, font, buf, packedLightCoords);
            renderLine(Component.literal("Folk job").withStyle(ChatFormatting.YELLOW), pose, baseOffset + 0.75f, opacity, font, buf, packedLightCoords);

            if (folk.getEntityData().get(EntityFolk.HOME).getY() == 999) {
                renderLine(Component.translatable("simukraftr.text.homeless").withStyle(ChatFormatting.YELLOW), pose, baseOffset + 0.50f, opacity, font, buf, packedLightCoords);
            } else {
                renderLine(Component.literal("Folk home").withStyle(ChatFormatting.YELLOW), pose, baseOffset + 0.50f, opacity, font, buf, packedLightCoords);
            }

            renderLine(Component.literal("Folk relationship status").withStyle(ChatFormatting.YELLOW), pose, baseOffset + 0.25f, opacity, font, buf, packedLightCoords);
            renderLine(Component.literal("Folk hunger").withStyle(ChatFormatting.YELLOW), pose, baseOffset, opacity, font, buf, packedLightCoords);
            return;
        }

        // Entity is within view, but not being looked at
        renderLine(Component.literal(folk.getEntityData().get(EntityFolk.FIRST_NAME) + " " + folk.getEntityData().get(EntityFolk.LAST_NAME) + "(" + folk.getEntityData().get(EntityFolk.AGE) + ")"), pose, baseOffset + 0.25f, opacity, font, buf, packedLightCoords);
        renderLine(Component.literal("Folk current task").withStyle(ChatFormatting.YELLOW), pose, baseOffset, opacity, font, buf, packedLightCoords);
    }

    private void renderLine(Component text, PoseStack pose, float offset, float opacity, Font font, MultiBufferSource buf, int packedLightCoords) {
        float width = (float) (-font.width(text) / 2);
        int opacityOut = (int) (opacity * 255.0F) << 24;
        pose.pushPose();
        pose.translate(0.0F, offset, 0.0F);
        pose.mulPose(this.entityRenderDispatcher.cameraOrientation());
        pose.scale(-0.025F, -0.025F, 0.025F);
        Matrix4f matrix4f = pose.last().pose();
        font.drawInBatch(text, width, 0, -1, false, matrix4f, buf, Font.DisplayMode.NORMAL, opacityOut, packedLightCoords);
        pose.popPose();
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull EntityFolk entityFolk) {
        if (entityFolk.getEntityData().get(EntityFolk.GENDER) == 0) {
            return new ResourceLocation(SimUKraft.MODID, "textures/entity/female" + entityFolk.getEntityData().get(EntityFolk.SKIN_ID) + ".png");
        }
        return new ResourceLocation(SimUKraft.MODID, "textures/entity/male" + entityFolk.getEntityData().get(EntityFolk.SKIN_ID) + ".png");
    }
}
