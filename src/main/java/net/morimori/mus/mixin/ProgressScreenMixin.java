package net.morimori.mus.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.ProgressScreen;
import net.morimori.mus.RenderUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ProgressScreen.class)
public class ProgressScreenMixin {
    @Inject(method = "<init>", at = @At("TAIL"))
    private  void init(boolean bl, CallbackInfo ci) {
        RenderUtils.resetMemoryBar();
    }

    @Inject(method = "render", at = @At("TAIL"))
    private void render(PoseStack poseStack, int i, int j, float f, CallbackInfo ci) {
        RenderUtils.renderMemoryBar(poseStack, 1, true, true);
    }
}
