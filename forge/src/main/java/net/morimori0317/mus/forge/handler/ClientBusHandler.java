package net.morimori0317.mus.forge.handler;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.morimori0317.mus.MUSKeyMapping;
import net.morimori0317.mus.handler.RenderHandler;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientBusHandler {
    private static final IGuiOverlay MEMORY_OVERLAY = (forgeGui, poseStack, f, i, j) -> {
        RenderHandler.onHudRender(poseStack, f);
    };

    @SubscribeEvent
    public static void onRegisterGuiOverlay(RegisterGuiOverlaysEvent e) {
        e.registerAboveAll("memoryusagescreen_memorybar", MEMORY_OVERLAY);
    }

    @SubscribeEvent
    public static void onRegisterKey(RegisterKeyMappingsEvent e) {
        e.register(MUSKeyMapping.showMemoryBarKey);
    }
}
