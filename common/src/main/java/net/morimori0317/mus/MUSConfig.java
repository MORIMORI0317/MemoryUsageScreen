package net.morimori0317.mus;

public interface MUSConfig {
    MUSConfig DEFAULT = new DefaultMUSConfig();

    boolean isEnableInitLoadingScreen();

    boolean isEnableWorldLoadingScreen();

    boolean isEnableToggleMode();
}
