package net.linkedto.stackable.fabric;

import net.fabricmc.loader.api.FabricLoader;
import net.linkedto.stackable.Stackable;
import net.linkedto.stackable.StackableConfig;
import net.fabricmc.api.ModInitializer;

public final class StackableFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        StackableConfig.setConfigDir(FabricLoader.getInstance().getConfigDir());
        Stackable.init();
    }
}
