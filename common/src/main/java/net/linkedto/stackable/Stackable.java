package net.linkedto.stackable;

import net.minecraft.world.item.ItemStack;

public final class Stackable {
    public static final String MOD_ID = "stackable";

    public static int getConfiguredSize(ItemStack stack) {
        return StackableConfig.getConfiguredSize(stack.getItem());
    }

    public static void init() {
        StackableConfig.load();
    }
}
