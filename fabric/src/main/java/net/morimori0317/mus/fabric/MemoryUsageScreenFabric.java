package net.morimori0317.mus.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.morimori0317.mus.MUSKeyMapping;
import net.morimori0317.mus.MemoryUsageScreen;
import net.morimori0317.mus.fabric.handler.ClientHandlerFabric;
import net.morimori0317.mus.fabric.handler.RenderHandlerFabric;

public class MemoryUsageScreenFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MemoryUsageScreen.init();
        RenderHandlerFabric.init();
        ClientHandlerFabric.init();

        KeyBindingHelper.registerKeyBinding(MUSKeyMapping.showMemoryBarKey);
    }
}
