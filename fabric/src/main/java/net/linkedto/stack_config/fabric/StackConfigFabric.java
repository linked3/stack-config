package net.linkedto.stack_config.fabric;

import net.linkedto.stack_config.StackConfig;
import net.fabricmc.api.ModInitializer;

public final class StackConfigFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        StackConfig.init();
    }
}
