package net.morimori0317.mus.fabric.handler;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.morimori0317.mus.handler.ClientHandler;

public class ClientHandlerFabric {
    public static void init() {
        ClientTickEvents.START_CLIENT_TICK.register(ClientHandlerFabric::onStartTick);
    }

    private static void onStartTick(Minecraft minecraft) {
        ClientHandler.onTick();
    }
}
