package net.morimori.mus;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.LoadingOverlay;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RenderHandler {
    private static final Minecraft mc = Minecraft.getInstance();

    @SubscribeEvent
    public static void onRender(TickEvent.RenderTickEvent e) {
        if (MemoryUsageScreen.enableShowMemoryUsage || (ClientConfig.enableInitLoadingScreen.get() && mc.getOverlay() instanceof LoadingOverlay) || (ClientConfig.enableWorldLoadingScreen.get() && mc.screen != null && MemoryUsageScreen.RENDER_SCREENS.contains(mc.screen.getClass())))
            MemoryUsageScreen.overlay.render(new PoseStack(), 1, true, !(mc.getOverlay() instanceof LoadingOverlay));
    }
}
