package net.morimori0317.mus;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.ConnectScreen;
import net.minecraft.client.gui.screens.LevelLoadingScreen;
import net.minecraft.client.gui.screens.ProgressScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClientHandler {
    @SubscribeEvent
    public static void onKeyPress(ScreenEvent.KeyboardKeyPressedEvent e) {
        MemoryUsageManager.getInstance().onGUIKeyPress(e.getKeyCode());
    }

    @SubscribeEvent
    public static void onTick(TickEvent.ClientTickEvent e) {
        if (e.phase == TickEvent.Phase.START)
            MemoryUsageManager.getInstance().onTick();
    }

    @SubscribeEvent
    public static void onRender(TickEvent.RenderTickEvent e) {
        if (e.phase == TickEvent.Phase.END)
            MemoryUsageManager.getInstance().onRender(new PoseStack());
    }

    @SubscribeEvent
    public static void onGuiRender(ScreenEvent.DrawScreenEvent e) {
        Screen screen = e.getScreen();
        if (screen instanceof ConnectScreen || screen instanceof LevelLoadingScreen || screen instanceof ProgressScreen)
            MemoryUsageManager.getInstance().onWorldLoadingRender(e.getPoseStack());
    }

    @SubscribeEvent
    public static void onGuiInit(ScreenEvent.InitScreenEvent e) {
        Screen screen = e.getScreen();
        if (screen instanceof ConnectScreen || screen instanceof LevelLoadingScreen || screen instanceof ProgressScreen)
            MemoryUsageManager.getInstance().onWorldLoadingReset();
    }
}