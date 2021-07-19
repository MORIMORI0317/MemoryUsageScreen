package net.morimori.mus;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class MemoryUsageScreen implements ClientModInitializer {
    public static final String MODID = "memoryusagescreen";
    public static final MemoryUsageOverlay overlay = new MemoryUsageOverlay();
    public static final KeyMapping SHOW_MEMORYUSAGE = new KeyMapping("key.memoryusagescreen.show", GLFW.GLFW_KEY_U, "key.categories.memoryusagescreen");
    public static MUSConfig CONFIG;
    public static boolean enableShowMemoryUsage;

    @Override
    public void onInitializeClient() {
        CONFIG = AutoConfig.register(MUSConfig.class, Toml4jConfigSerializer::new).getConfig();
        KeyBindingHelper.registerKeyBinding(SHOW_MEMORYUSAGE);
    }
}
