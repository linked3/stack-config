package net.linkedto.stack_config.forge;

import net.linkedto.stack_config.StackConfig;
import net.minecraftforge.fml.common.Mod;

@Mod(StackConfig.MOD_ID)
public final class StackConfigForge {
    public StackConfigForge() {
        StackConfig.init();
    }
}
