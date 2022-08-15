package net.morimori0317.mus;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.resources.metadata.texture.TextureMetadataSection;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.VanillaPackResources;
import net.minecraft.server.packs.resources.ResourceManager;

import java.io.IOException;
import java.io.InputStream;

public class LoadingTexture extends SimpleTexture {
    private final ResourceLocation location;

    public LoadingTexture(ResourceLocation location) {
        super(location);
        this.location = location;
    }

    protected TextureImage getTextureImage(ResourceManager resourceManager) {
        Minecraft minecraft = Minecraft.getInstance();
        VanillaPackResources vanillaPackResources = minecraft.getClientPackSource().getVanillaPack();

        try (InputStream inputStream = vanillaPackResources.getResource(PackType.CLIENT_RESOURCES, location)) {
            return new TextureImage(new TextureMetadataSection(true, true), NativeImage.read(inputStream));
        } catch (IOException ex) {
            return new TextureImage(ex);
        }
    }
}
