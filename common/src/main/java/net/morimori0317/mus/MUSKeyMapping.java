package net.morimori0317.mus;

import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class MUSKeyMapping {
    public static final KeyMapping showMemoryBarKey = new KeyMapping("key.memoryusagescreen.show", GLFW.GLFW_KEY_U, "key.categories.memoryusagescreen");
    private static boolean enable;

    public static void tick() {
        if (MemoryUsageScreen.getConfig().isEnableToggleMode()) {
            while (showMemoryBarKey.consumeClick()) {
                enable = !enable;
            }
        } else {
            enable = showMemoryBarKey.isDown();
        }
    }

    public static boolean enableShoMemoryKey() {
        return enable;
    }
}
