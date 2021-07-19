package net.morimori.mus.mixin;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.morimori.mus.MemoryUsageScreen;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Shadow
    @Nullable
    public LocalPlayer player;
    private boolean resetFlg;
    private boolean pushFlg;
    private boolean spushFlg;

    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo ci) {
        boolean press = InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), MemoryUsageScreen.SHOW_MEMORYUSAGE.key.getValue());
        if (MemoryUsageScreen.CONFIG.enableToggleMode) {
            if (press && !spushFlg) pushFlg = true;

            if (pushFlg) {
                MemoryUsageScreen.enableShowMemoryUsage = !MemoryUsageScreen.enableShowMemoryUsage;
            }

            spushFlg = true;
            pushFlg = false;

            if (!press) {
                spushFlg = false;
            }
        } else {
            MemoryUsageScreen.enableShowMemoryUsage = press;
            if (press) {
                resetFlg = true;
            } else if (resetFlg) {
                resetFlg = false;
                MemoryUsageScreen.overlay.reset();
            }
        }
    }
}
