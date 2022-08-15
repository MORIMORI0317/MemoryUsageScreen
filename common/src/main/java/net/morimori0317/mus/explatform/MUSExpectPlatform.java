package net.morimori0317.mus.explatform;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.morimori0317.mus.MUSConfig;

public class MUSExpectPlatform {
    @ExpectPlatform
    public static boolean isClothConfigLoaded() {
        throw new AssertionError();
    }
}
