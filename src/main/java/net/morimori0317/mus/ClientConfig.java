package net.morimori0317.mus;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class ClientConfig {
    public static ForgeConfigSpec.ConfigValue<Boolean> enableInitLoadingScreen;
    public static ForgeConfigSpec.ConfigValue<Boolean> enableWorldLoadingScreen;
    public static ForgeConfigSpec.ConfigValue<Boolean> enableToggleMode;

    public static void init() {
        Pair<ConfigLoder, ForgeConfigSpec> client_config = new ForgeConfigSpec.Builder().configure(ConfigLoder::new);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, client_config.getRight());
    }

    public static class ConfigLoder {
        public ConfigLoder(ForgeConfigSpec.Builder builder) {
            builder.push("Memory Bar");
            enableInitLoadingScreen = builder.define("Enable display on the initial loading screen", true);
            enableWorldLoadingScreen = builder.define("Enable display on the world loading screen", true);
            enableToggleMode = builder.define("Enable toggle display mode", false);
            builder.pop();
        }
    }
}
