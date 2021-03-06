package net.morimori0317.mus;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkConstants;
import org.lwjgl.glfw.GLFW;

@Mod(MemoryUsageScreen.MODID)
public class MemoryUsageScreen {
    public static final String MODID = "memoryusagescreen";
    public static final KeyMapping showMemoryBarKey = new KeyMapping("key.memoryusagescreen.show", GLFW.GLFW_KEY_U, "key.categories.memoryusagescreen");

    public MemoryUsageScreen() {
        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> NetworkConstants.IGNORESERVERONLY, (remote, isServer) -> true));
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        ClientConfig.init();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(ClientHandler.class);
        ClientRegistry.registerKeyBinding(showMemoryBarKey);
    }

    public static boolean isConfigEnableInitLoadingScreen() {
        return ClientConfig.enableInitLoadingScreen.get();
    }

    public static boolean isConfigEnableWorldLoadingScreen() {
        return ClientConfig.enableWorldLoadingScreen.get();
    }

    public static boolean isConfigEnableToggleMode() {
        return ClientConfig.enableToggleMode.get();
    }
}