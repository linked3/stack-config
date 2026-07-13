package net.linkedto.stack_config.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.linkedto.stack_config.Config;
import net.linkedto.stack_config.StackConfig;

public final class StackConfigFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Config.setConfigDir(FabricLoader.getInstance().getConfigDir());
        StackConfig.init();
    }
}
