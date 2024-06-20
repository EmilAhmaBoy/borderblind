package dev.emilahmaboy.borderblind.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;


@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    @ModifyConstant(
            method = "drawBlockOutline",
            constant = @Constant(
                    floatValue = 0.0F
            )
    )
    private float modifyOutlineColor(float constant, MatrixStack matrices, VertexConsumer vertexConsumer, Entity entity, double cameraX, double cameraY, double cameraZ, BlockPos pos, BlockState state) {
        int color = state.getRegistryEntry().value().getDefaultMapColor().color;

        float r = (float) (color >> 16 & 255) / 255.0F;
        float g = (float) (color >> 8 & 255) / 255.0F;
        float b = (float) (color & 255) / 255.0F;

        if (r + g + b < 1.5F) {
            return 1.0F;
        } else {
            return 0.0F;
        }
    }
}
