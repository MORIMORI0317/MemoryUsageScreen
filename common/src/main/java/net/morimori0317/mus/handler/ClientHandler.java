package net.morimori0317.mus.handler;

import net.minecraft.client.gui.screens.Screen;
import net.morimori0317.mus.MUSKeyMapping;
import net.morimori0317.mus.api.MemoryUsageScreenAPI;

public class ClientHandler {
    public static void onScreenInit(Screen screen) {
        if (MemoryUsageScreenAPI.getInstance().isEnableScreen(screen))
            MemoryUsageScreenAPI.getInstance().getOverlay().reset();
    }

    public static void onTick() {
        MUSKeyMapping.tick();
    }
}
