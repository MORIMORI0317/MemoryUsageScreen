package net.morimori.mus;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.screens.ConnectScreen;
import net.minecraft.client.gui.screens.LevelLoadingScreen;
import net.minecraft.client.gui.screens.ProgressScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmlclient.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

@Mod(MemoryUsageScreen.MODID)
public class MemoryUsageScreen {
    public static final String MODID = "memoryusagescreen";
    public static final MemoryUsageOverlay overlay = new MemoryUsageOverlay();
    public static final KeyMapping SHOW_MEMORYUSAGE = new KeyMapping("key.memoryusagescreen.show", GLFW.GLFW_KEY_U, "key.categories.memoryusagescreen");
    public static final List<Class<? extends Screen>> RENDER_SCREENS = new ArrayList<>();
    public static boolean enableShowMemoryUsage;

    public MemoryUsageScreen() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        ClientConfig.init();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(ClientHandler.class);
        MinecraftForge.EVENT_BUS.register(RenderHandler.class);
        ClientRegistry.registerKeyBinding(SHOW_MEMORYUSAGE);

        RENDER_SCREENS.add(ConnectScreen.class);
        RENDER_SCREENS.add(LevelLoadingScreen.class);
        RENDER_SCREENS.add(ProgressScreen.class);
    }
}
