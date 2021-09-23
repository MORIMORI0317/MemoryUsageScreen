package net.morimori.mus;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.ConnectScreen;
import net.minecraft.client.gui.screens.LevelLoadingScreen;
import net.minecraft.client.gui.screens.ProgressScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClientHandler {
    @SubscribeEvent
    public static void onKeyPress(GuiScreenEvent.KeyboardKeyPressedEvent e) {
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
    public static void onGuiRender(GuiScreenEvent.DrawScreenEvent e) {
        Screen screen = e.getGui();
        if (screen instanceof ConnectScreen || screen instanceof LevelLoadingScreen || screen instanceof ProgressScreen)
            MemoryUsageManager.getInstance().onWorldLoadingRender(e.getMatrixStack());
    }

    @SubscribeEvent
    public static void onGuiInit(GuiScreenEvent.InitGuiEvent e) {
        Screen screen = e.getGui();
        if (screen instanceof ConnectScreen || screen instanceof LevelLoadingScreen || screen instanceof ProgressScreen)
            MemoryUsageManager.getInstance().onWorldLoadingReset();
    }
}
