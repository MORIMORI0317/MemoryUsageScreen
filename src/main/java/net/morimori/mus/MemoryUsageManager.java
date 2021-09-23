package net.morimori.mus;

import com.mojang.blaze3d.vertex.PoseStack;

public class MemoryUsageManager {
    private static final MemoryUsageManager INSTANCE = new MemoryUsageManager();
    public final MemoryUsageOverlay overlay = new MemoryUsageOverlay();
    public boolean enableShowMemoryUsage;

    public static MemoryUsageManager getInstance() {
        return INSTANCE;
    }

    public void toggleShowMemoryUsage() {
        setShowMemoryUsageEnabled(!enableShowMemoryUsage);
    }

    public void setShowMemoryUsageEnabled(boolean enabled) {
        if (!enabled && enableShowMemoryUsage) {
            overlay.reset();
        }
        enableShowMemoryUsage = enabled;
    }

    public void onTick() {
        if (MemoryUsageScreen.isConfigEnableToggleMode()) {
            while (MemoryUsageScreen.showMemoryBarKey.consumeClick()) {
                toggleShowMemoryUsage();
            }
        } else {
            boolean press = MemoryUsageScreen.showMemoryBarKey.isDown();
            setShowMemoryUsageEnabled(press);
        }
    }

    public void onGUIKeyPress(int code) {
        if (code == MemoryUsageScreen.showMemoryBarKey.getKey().getValue())
            toggleShowMemoryUsage();
    }

    public void onRender(PoseStack poseStack) {
        if (enableShowMemoryUsage)
            overlay.render(poseStack, 1, true, true);
    }

    public void onWorldLoadingReset() {
        if (MemoryUsageScreen.isConfigEnableWorldLoadingScreen())
            overlay.reset();
    }

    public void onWorldLoadingRender(PoseStack poseStack) {
        if (MemoryUsageScreen.isConfigEnableWorldLoadingScreen() && !enableShowMemoryUsage)
            overlay.render(poseStack, 1, true, true);
    }

    public void onLoadingRender(PoseStack poseStack, float alpha, boolean useFont, boolean bloackGrond) {
        if (MemoryUsageScreen.isConfigEnableInitLoadingScreen() && !enableShowMemoryUsage)
            overlay.render(poseStack, alpha, useFont, bloackGrond);
    }
}
