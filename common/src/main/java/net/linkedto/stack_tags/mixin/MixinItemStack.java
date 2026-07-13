package net.linkedto.stack_tags.mixin;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class MixinItemStack {
    private static final TagKey<Item> STACKABLE_TO_1 = TagKey.create(Registries.ITEM, new ResourceLocation("stack_tags", "stackable_to_1"));
    private static final TagKey<Item> STACKABLE_TO_2 = TagKey.create(Registries.ITEM, new ResourceLocation("stack_tags", "stackable_to_2"));
    private static final TagKey<Item> STACKABLE_TO_4 = TagKey.create(Registries.ITEM, new ResourceLocation("stack_tags", "stackable_to_4"));
    private static final TagKey<Item> STACKABLE_TO_8 = TagKey.create(Registries.ITEM, new ResourceLocation("stack_tags", "stackable_to_8"));
    private static final TagKey<Item> STACKABLE_TO_16 = TagKey.create(Registries.ITEM, new ResourceLocation("stack_tags", "stackable_to_16"));
    private static final TagKey<Item> STACKABLE_TO_32 = TagKey.create(Registries.ITEM, new ResourceLocation("stack_tags", "stackable_to_32"));
    private static final TagKey<Item> STACKABLE_TO_64 = TagKey.create(Registries.ITEM, new ResourceLocation("stack_tags", "stackable_to_64"));

    @Inject(method = "getMaxStackSize()I", at = @At("RETURN"), cancellable = true)
    private void examplemod$adjustMaxStackSize(CallbackInfoReturnable<Integer> cir) {
        ItemStack self = (ItemStack) (Object) this;

        int matched = -1;
        if (self.is(STACKABLE_TO_1)) matched = Math.max(matched, 1);
        if (self.is(STACKABLE_TO_2)) matched = Math.max(matched, 2);
        if (self.is(STACKABLE_TO_4)) matched = Math.max(matched, 4);
        if (self.is(STACKABLE_TO_8)) matched = Math.max(matched, 8);
        if (self.is(STACKABLE_TO_16)) matched = Math.max(matched, 16);
        if (self.is(STACKABLE_TO_32)) matched = Math.max(matched, 32);
        if (self.is(STACKABLE_TO_64)) matched = Math.max(matched, 64);

        if (matched > 0) {
            int clamped = Math.min(matched, 64);
            cir.setReturnValue(clamped);
        }
    }
}
