package net.morimori.mus.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.LevelLoadingScreen;
import net.minecraft.server.level.progress.StoringChunkProgressListener;
import net.morimori.mus.MemoryUsageScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelLoadingScreen.class)
public class LevelLoadingScreenMixin {
    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(StoringChunkProgressListener storingChunkProgressListener, CallbackInfo ci) {
        if (MemoryUsageScreen.CONFIG.enableWorldLoadingScreen)
            MemoryUsageScreen.overlay.reset();
    }

    @Inject(method = "render", at = @At("TAIL"))
    private void render(PoseStack poseStack, int i, int j, float f, CallbackInfo ci) {
        if (MemoryUsageScreen.CONFIG.enableWorldLoadingScreen && !MemoryUsageScreen.enableShowMemoryUsage)
            MemoryUsageScreen.overlay.render(poseStack, 1, true, true);
    }
}
