package net.morimori0317.mus.mixin;

import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.LoadingOverlay;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.morimori0317.mus.LoadingTexture;
import net.morimori0317.mus.MemoryUsageOverlay;
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

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/LoadingOverlay;drawProgressBar(Lnet/minecraft/client/gui/GuiGraphics;IIIIF)V"))
    private void render(GuiGraphics guiGraphics, int i, int j, float f, CallbackInfo ci) {
        if (MemoryUsageScreen.getConfig().isEnableInitLoadingScreen()) {
            long m = Util.getMillis();
            float g = this.fadeOutStart > -1L ? (float) (m - this.fadeOutStart) / 1000.0F : -1.0F;
            MemoryUsageScreenAPI.getInstance().getOverlay().render(guiGraphics, 1.0F - Mth.clamp(g, 0.0F, 1.0F), false, false, f);
        }
    }

    @Inject(method = "registerTextures", at = @At("HEAD"))
    private static void registerTextures(Minecraft minecraft, CallbackInfo ci) {
        minecraft.getTextureManager().register(MemoryUsageOverlay.FONT_LOCATION, new LoadingTexture(new ResourceLocation("textures/font/ascii.png")));
    }
}
