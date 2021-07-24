package net.morimori.mus;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClientHandler {
    private static final Minecraft mc = Minecraft.getInstance();
    private static boolean resetFlg;
    private static boolean pushFlg;
    private static boolean spushFlg;

    @SubscribeEvent
    public static void onTick(TickEvent.ClientTickEvent e) {
        boolean press = InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), MemoryUsageScreen.SHOW_MEMORYUSAGE.getKey().getValue());
        if (ClientConfig.enableToggleMode.get()) {
            if (press && !spushFlg) pushFlg = true;

            if (pushFlg) {
                MemoryUsageScreen.enableShowMemoryUsage = !MemoryUsageScreen.enableShowMemoryUsage;
            }

            spushFlg = true;
            pushFlg = false;

            if (!press) {
                spushFlg = false;
            }
        } else {
            MemoryUsageScreen.enableShowMemoryUsage = press;
            if (press) {
                resetFlg = true;
            } else if (resetFlg) {
                resetFlg = false;
                MemoryUsageScreen.overlay.reset();
            }
        }
    }
}
