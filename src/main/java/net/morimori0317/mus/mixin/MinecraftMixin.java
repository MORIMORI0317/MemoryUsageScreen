package net.morimori0317.mus.mixin;

import net.minecraft.client.Minecraft;
import net.morimori0317.mus.MemoryUsageScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo ci) {
        if (MemoryUsageScreen.CONFIG.enableToggleMode) {
            while (MemoryUsageScreen.SHOW_MEMORYUSAGE.consumeClick()) {
                MemoryUsageScreen.toggleShowMemoryUsage();
            }
        } else {
            boolean press = MemoryUsageScreen.SHOW_MEMORYUSAGE.isDown();
            MemoryUsageScreen.setShowMemoryUsageEnabled(press);
        }
    }
}
