package net.morimori0317.mus;

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

    public static void toggleShowMemoryUsage() {
        setShowMemoryUsageEnabled(!enableShowMemoryUsage);
    }

    public static void setShowMemoryUsageEnabled(boolean enabled) {
        if (!enabled && enableShowMemoryUsage) {
            overlay.reset();
        }
        enableShowMemoryUsage = enabled;
    }

    @Override
    public void onInitializeClient() {
        CONFIG = AutoConfig.register(MUSConfig.class, Toml4jConfigSerializer::new).getConfig();
        KeyBindingHelper.registerKeyBinding(SHOW_MEMORYUSAGE);

        // with MAIN_WINDOW_BIT the keybinding will now be triggered always whether there is a gui or not
        // to not receive the trigger twice when there is no gui we also use NO_VANILLA_BIT
       // KTIG.registerKeyBindingForTriggerPoints(SHOW_MEMORYUSAGE, KeyBindingTriggerPoints.MAIN_WINDOW_BIT | KeyBindingTriggerPoints.NO_VANILLA_BIT);
    }
}