package net.morimori0317.mus.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.LoadingOverlay;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.morimori0317.mus.LoadingTexture;
import net.morimori0317.mus.MemoryUsageScreen;
import net.morimori0317.mus.api.MemoryUsageScreenAPI;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LoadingOverlay.class)
public abstract class LoadingOverlayMixin {
    @Shadow
    private long fadeOutStart;

    private static final ResourceLocation FONT_LOCATION = new ResourceLocation(MemoryUsageScreen.MODID, "fonts");
    private static final Minecraft mc = Minecraft.getInstance();

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/LoadingOverlay;drawProgressBar(Lcom/mojang/blaze3d/vertex/PoseStack;IIIIF)V"))
    private void render(PoseStack poseStack, int i, int j, float f, CallbackInfo ci) {
        if (MemoryUsageScreen.getConfig().isEnableInitLoadingScreen()) {
            long m = Util.getMillis();
            float g = this.fadeOutStart > -1L ? (float) (m - this.fadeOutStart) / 1000.0F : -1.0F;
            MemoryUsageScreenAPI.getInstance().getOverlay().render(poseStack, 1.0F - Mth.clamp(g, 0.0F, 1.0F), false, false, f);
        }
    }

    @Inject(method = "registerTextures", at = @At("HEAD"))
    private static void registerTextures(Minecraft minecraft, CallbackInfo ci) {
        mc.getTextureManager().register(FONT_LOCATION, new LoadingTexture(new ResourceLocation("textures/font/ascii.png")));
    }
}
