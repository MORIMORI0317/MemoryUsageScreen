package net.morimori0317.mus;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;

public class MemoryUsageOverlay {
    public static final ResourceLocation FONT_LOCATION = new ResourceLocation(MemoryUsageScreen.MODID, "fonts");
    private static final Minecraft mc = Minecraft.getInstance();
    private float maxBar;

    public void render(GuiGraphics guiGraphics, float alpha, boolean useFont, boolean bloackGrond, float delta) {
        PoseStack poseStack = guiGraphics.pose();

        poseStack.pushPose();
        int colw = FastColor.ARGB32.color(Math.round(alpha * 255.0F), 255, 255, 255);

        long max = Runtime.getRuntime().maxMemory();
        long total = Runtime.getRuntime().totalMemory();
        long free = Runtime.getRuntime().freeMemory();
        long usage = total - free;
        float currentUsage = (float) usage / (float) max;
        float currentAllocated = (float) total / (float) max;

        float wringWar = 1f / 3f;
        float cr = (Mth.clamp(currentUsage, wringWar, wringWar * 2) - wringWar) / wringWar;
        float cg = 1f - (Mth.clamp(currentUsage, wringWar * 2, 1f) - wringWar * 2) / wringWar;

        int colmem = FastColor.ARGB32.color(Math.round(alpha * 255.0F), Math.round(cr * 255.0F), Math.round(cg * 255.0F), 0x00);

        int sw = mc.getWindow().getGuiScaledWidth();
        int sh = mc.getWindow().getGuiScaledHeight();

        int w = 246;
        int h = 11;

        int sx = sw / 2 - w / 2;
        int sy = (int) (sh * 0.08D);

        if (currentUsage > maxBar) {
            maxBar = currentUsage;
        }

        renderCenterString("Memory Used / Total", guiGraphics, sw / 2, sy - 10, 1, 1, 1, alpha, useFont);

        if (bloackGrond)
            cfill(guiGraphics, sx, sy, w, h, FastColor.ARGB32.color(Math.round(alpha * 255.0F), 0, 0, 0));

        cfill(guiGraphics, sx, sy, 1, h, colw);
        cfill(guiGraphics, sx + w - 1, sy, 1, h, colw);
        cfill(guiGraphics, sx + 1, sy, w - 2, 1, colw);
        cfill(guiGraphics, sx + 1, sy + h - 1, w - 2, 1, colw);
        cfill(guiGraphics, sx + 2, sy + 2, (int) ((w - 4) * currentUsage), h - 4, colmem);
        cfill(guiGraphics, sx + 2 + (int) ((w - 4) * maxBar), sy + 1, 1, h - 2, FastColor.ARGB32.color(Math.round(alpha * 255.0F), 0, 0, 255));
        cfill(guiGraphics, sx + 2 + (int) ((w - 4) * currentAllocated), sy + 1, 1, h - 2, FastColor.ARGB32.color(Math.round(alpha * 255.0F), bloackGrond ? 255 : 0, 0, 0));

        renderCenterString(String.format("%03d/%03dMB", bytesToMegabytes(usage), bytesToMegabytes(max)), guiGraphics, sw / 2, sy + 2, 1, 1, 1, alpha, useFont);
        poseStack.popPose();
    }

    private void cfill(GuiGraphics guiGraphics, int x, int y, int w, int h, int col) {
        guiGraphics.fill(x, y, x + w, y + h, col);
    }

    private void renderCenterString(String str, GuiGraphics guiGraphics, int x, int y, float r, float g, float b, float a, boolean useFont) {
        if (!useFont) {
            str = str.toUpperCase();
        }

        int size = useFont ? mc.font.width(str) : str.length() * 6;

        if (useFont) {
            int col = FastColor.ARGB32.color(Math.round(a * 255.0F), Math.round(r * 255.0F), Math.round(g * 255.0F), Math.round(b * 255.0F));
            guiGraphics.drawString(mc.font, str, (int) (x - (float) size / 2), y, col);
        } else {
            renderNoFontString(str, guiGraphics, x - size / 2, y, a, r, g, b);
        }


    }

    private void renderNoFontString(String str, GuiGraphics guiGraphics, int x, int y, float alpha, float r, float g, float b) {
        for (int a = 0; a < str.length(); a++) {
            char ch = str.charAt(a);
            if (ch > 0xFF) {
                continue;
            }
            int tsx = (ch & 0x0F) * 8;
            int tsy = (ch >> 4 & 0x0F) * 8;
            drawTexture(FONT_LOCATION, guiGraphics, x + a * 6, y, tsx, tsy, 6, 7, 128, 128, r, g, b, alpha);
        }
    }

    private long bytesToMegabytes(long l) {
        return l / 1024L / 1024L;
    }

    private void drawTexture(ResourceLocation location, GuiGraphics guiGraphics, int x, int y, int textureStartX, int textureStartY, int textureFinishWidth, int textureFinishHeight, int textureSizeX,
                             int textureSizeY, float r, float g, float b, float a) {
        PoseStack psstack = guiGraphics.pose();
        psstack.pushPose();

        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(770, 1);

        guiGraphics.setColor(r, g, b, a);
        guiGraphics.blit(location, x, y, textureStartX, textureStartY, textureFinishWidth, textureFinishHeight, textureSizeX, textureSizeY);
        guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);

        RenderSystem.defaultBlendFunc();
        RenderSystem.disableBlend();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();

        psstack.popPose();
    }

    public void reset() {
        maxBar = 0;
    }
}
