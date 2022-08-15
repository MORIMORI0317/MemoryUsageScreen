package net.morimori0317.mus.explatform;

import dev.architectury.injectables.annotations.ExpectPlatform;

public class MUSExpectPlatform {
    @ExpectPlatform
    public static boolean isClothConfigLoaded() {
        throw new AssertionError();
    }
}
