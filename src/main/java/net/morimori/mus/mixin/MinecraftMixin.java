package net.morimori.mus.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.Minecraft;
import net.morimori.mus.MemoryUsageScreen;

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
