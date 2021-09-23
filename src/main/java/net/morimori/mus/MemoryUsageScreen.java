package net.morimori.mus;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmlclient.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;

@Mod(MemoryUsageScreen.MODID)
public class MemoryUsageScreen {
    public static final String MODID = "memoryusagescreen";
    public static final KeyMapping showMemoryBarKey = new KeyMapping("key.memoryusagescreen.show", GLFW.GLFW_KEY_U, "key.categories.memoryusagescreen");


    public MemoryUsageScreen() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(ClientHandler.class);
        ClientRegistry.registerKeyBinding(showMemoryBarKey);
    }

    public static boolean isConfigEnableInitLoadingScreen() {
        return true;
    }

    public static boolean isConfigEnableWorldLoadingScreen() {
        return true;
    }

    public static boolean isConfigEnableToggleMode() {
        return true;
    }
}
