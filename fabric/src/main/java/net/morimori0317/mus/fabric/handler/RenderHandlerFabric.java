package net.morimori0317.mus.fabric.handler;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.morimori0317.mus.handler.ClientHandler;
import net.morimori0317.mus.handler.RenderHandler;

public class RenderHandlerFabric {
    public static void init() {
        ScreenEvents.AFTER_INIT.register(RenderHandlerFabric::onScreenAfterInit);
        HudRenderCallback.EVENT.register(RenderHandlerFabric::onHudRender);
    }

    private static void onScreenAfterInit(Minecraft client, Screen screen, int scaledWidth, int scaledHeight) {
      /*  if (MemoryUsageScreenAPI.getInstance().isEnableScreen(screen))
            ScreenEvents.afterRender(screen).register((screen1, matrices, mouseX, mouseY, tickDelta) -> RenderHandler.onScreenRender(matrices, screen1, tickDelta));*/

        ClientHandler.onScreenInit(screen);
    }

    private static void onHudRender(GuiGraphics guiGraphics, float tickDelta) {
        RenderHandler.onHudRender(guiGraphics, tickDelta);
    }
}
