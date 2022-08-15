package net.morimori0317.mus;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.minecraft.client.gui.screens.Screen;

@Config(name = MemoryUsageScreen.MODID)
public class ClothMUSConfig implements MUSConfig, ConfigData {
    public boolean enableInitLoadingScreen = true;
    public boolean enableWorldLoadingScreen = true;
    public boolean enableToggleMode = false;

    @Override
    public boolean isEnableInitLoadingScreen() {
        return enableInitLoadingScreen;
    }

    @Override
    public boolean isEnableWorldLoadingScreen() {
        return enableWorldLoadingScreen;
    }

    @Override
    public boolean isEnableToggleMode() {
        return enableToggleMode;
    }

    public static ClothMUSConfig createConfig() {
        return AutoConfig.register(ClothMUSConfig.class, Toml4jConfigSerializer::new).getConfig();
    }

    public static Screen createConfigScreen(Screen parent) {
        return AutoConfig.getConfigScreen(ClothMUSConfig.class, parent).get();
    }
}
