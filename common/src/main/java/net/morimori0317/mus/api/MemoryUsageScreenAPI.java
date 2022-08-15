package net.morimori0317.mus.api;

import net.minecraft.client.gui.screens.Screen;
import net.morimori0317.mus.MemoryUsageOverlay;
import net.morimori0317.mus.MemoryUsageScreen;

public interface MemoryUsageScreenAPI {
    static MemoryUsageScreenAPI getInstance() {
        return MemoryUsageScreen.getAPI();
    }

    MemoryUsageOverlay getOverlay();

    boolean isEnableScreen(Screen screen);
}
