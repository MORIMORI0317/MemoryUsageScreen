package net.morimori0317.mus.forge.handler;

import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.morimori0317.mus.api.MemoryUsageScreenAPI;
import net.morimori0317.mus.handler.RenderHandler;

public class RenderHandlerForge {
    @SubscribeEvent
    public static void onScreenRenderPost(ScreenEvent.Render.Post e) {
        if (MemoryUsageScreenAPI.getInstance().isEnableScreen(e.getScreen()))
            RenderHandler.onScreenRender(e.getGuiGraphics(), e.getScreen(), e.getPartialTick());
    }
}
