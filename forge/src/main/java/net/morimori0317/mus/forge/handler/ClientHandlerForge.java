package net.morimori0317.mus.forge.handler;

import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.morimori0317.mus.handler.ClientHandler;

public class ClientHandlerForge {
    @SubscribeEvent
    public static void onScreenInitPost(ScreenEvent.Init.Post e) {
        ClientHandler.onScreenInit(e.getScreen());
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent e) {
        if (e.phase == TickEvent.Phase.START)
            ClientHandler.onTick();
    }
}
