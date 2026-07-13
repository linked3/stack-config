package net.linkedto.stack_config;

import net.minecraft.world.item.ItemStack;

public final class StackConfig {
    public static final String MOD_ID = "stack_config";

    public static int getConfiguredSize(ItemStack stack) {
        return Config.getConfiguredSize(stack.getItem());
    }

    public static void init() {
        Config.load();
    }
}
