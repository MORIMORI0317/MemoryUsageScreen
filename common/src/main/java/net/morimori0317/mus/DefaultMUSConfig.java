package net.morimori0317.mus;

public class DefaultMUSConfig implements MUSConfig {
    @Override
    public boolean isEnableInitLoadingScreen() {
        return true;
    }

    @Override
    public boolean isEnableWorldLoadingScreen() {
        return true;
    }

    @Override
    public boolean isEnableToggleMode() {
        return false;
    }
}
