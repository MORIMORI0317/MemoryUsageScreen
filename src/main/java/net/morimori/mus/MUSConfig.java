package net.morimori.mus;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = MemoryUsageScreen.MODID)
public class MUSConfig implements ConfigData {
    public boolean enableInitLoadingScreen = true;
    public boolean enableWorldLoadingScreen = true;
    public boolean enableToggleMode = false;
}
