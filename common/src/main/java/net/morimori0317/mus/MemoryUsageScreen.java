package net.morimori0317.mus;

import net.morimori0317.mus.api.MemoryUsageScreenAPI;
import net.morimori0317.mus.explatform.MUSExpectPlatform;

public class MemoryUsageScreen {
    public static final String MODID = "memoryusagescreen";
    private static MUSConfig CONFIG;

    public static void init() {
    }

    public static MemoryUsageScreenAPI getAPI() {
        return MemoryUsageScreenAPImpl.INSTANCE;
    }

    public static MUSConfig getConfig() {
        if (CONFIG == null) {
            if (MUSExpectPlatform.isClothConfigLoaded()) {
                CONFIG = ClothMUSConfig.createConfig();
            } else {
                CONFIG = MUSConfig.DEFAULT;
            }
        }
        return CONFIG;
    }
}
