package net.morimori0317.mus.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.morimori0317.mus.MUSKeyMapping;
import net.morimori0317.mus.api.MemoryUsageScreenAPI;

public class RenderHandler {
    public static void onScreenRender(GuiGraphics guiGraphics, Screen screen, float delta) {
        if (MemoryUsageScreenAPI.getInstance().isEnableScreen(screen))
            MemoryUsageScreenAPI.getInstance().getOverlay().render(guiGraphics, 1, true, true, delta);
    }

    public static void onHudRender(GuiGraphics guiGraphics, float delta) {
        if (MUSKeyMapping.enableShoMemoryKey() && !MemoryUsageScreenAPI.getInstance().isEnableScreen(Minecraft.getInstance().screen))
            MemoryUsageScreenAPI.getInstance().getOverlay().render(guiGraphics, 1, true, true, delta);
    }
}
