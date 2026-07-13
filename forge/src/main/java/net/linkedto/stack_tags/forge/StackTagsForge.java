package net.linkedto.stack_tags.forge;

import net.linkedto.stack_tags.StackTags;
import net.minecraftforge.fml.common.Mod;

@Mod(StackTags.MOD_ID)
public final class StackTagsForge {
    public StackTagsForge() {
        // Run our common setup.
        StackTags.init();
    }
}
