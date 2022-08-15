package net.morimori0317.mus.explatform.fabric;

import net.fabricmc.loader.api.FabricLoader;

public class MUSExpectPlatformImpl {
    public static boolean isClothConfigLoaded() {
        return FabricLoader.getInstance().isModLoaded("cloth-config");
    }
}
