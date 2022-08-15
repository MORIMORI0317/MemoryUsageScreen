package net.morimori0317.mus.forge;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkConstants;
import net.morimori0317.mus.MemoryUsageScreen;
import net.morimori0317.mus.forge.handler.ClientHandlerForge;
import net.morimori0317.mus.forge.handler.RenderHandlerForge;

@Mod(MemoryUsageScreen.MODID)
public class MemoryUsageScreenForge {

    public MemoryUsageScreenForge() {
        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> NetworkConstants.IGNORESERVERONLY, (remote, isServer) -> true));
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(RenderHandlerForge.class);
        MinecraftForge.EVENT_BUS.register(ClientHandlerForge.class);
        MemoryUsageScreen.init();
    }
}
