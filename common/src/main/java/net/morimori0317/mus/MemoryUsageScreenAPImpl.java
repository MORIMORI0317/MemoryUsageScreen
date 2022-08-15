package net.morimori0317.mus;

import net.minecraft.client.gui.screens.*;
import net.morimori0317.mus.api.MemoryUsageRenderScreen;
import net.morimori0317.mus.api.MemoryUsageScreenAPI;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class MemoryUsageScreenAPImpl implements MemoryUsageScreenAPI {
    public static final MemoryUsageScreenAPImpl INSTANCE = new MemoryUsageScreenAPImpl();
    private final MemoryUsageOverlay OVERLAY = new MemoryUsageOverlay();

    @Override
    public MemoryUsageOverlay getOverlay() {
        return OVERLAY;
    }

    @Override
    public boolean isEnableScreen(Screen screen) {
        if (MemoryUsageScreen.getConfig().isEnableWorldLoadingScreen()) {
            if (screen instanceof ConnectScreen)
                return true;
            if (screen instanceof GenericDirtMessageScreen)
                return true;
            if (screen instanceof LevelLoadingScreen)
                return true;
            if (screen instanceof ProgressScreen)
                return true;
            return screen instanceof ReceivingLevelScreen;
        }

        return screen instanceof MemoryUsageRenderScreen memoryUsageRenderScreen && memoryUsageRenderScreen.isShowMemoryBar();
    }
}
