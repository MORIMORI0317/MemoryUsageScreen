package net.morimori0317.mus.explatform.forge;

import net.minecraftforge.fml.ModList;

public class MUSExpectPlatformImpl {
    public static boolean isClothConfigLoaded() {
        return ModList.get().isLoaded("cloth_config");
    }
}
