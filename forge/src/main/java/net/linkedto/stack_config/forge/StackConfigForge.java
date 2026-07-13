package net.linkedto.stack_config.forge;

import net.linkedto.stack_config.Config;
import net.linkedto.stack_config.StackConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(StackConfig.MOD_ID)
public final class StackConfigForge {
    public StackConfigForge() {
        Config.setConfigDir(FMLPaths.CONFIGDIR.get());
        StackConfig.init();
    }
}
