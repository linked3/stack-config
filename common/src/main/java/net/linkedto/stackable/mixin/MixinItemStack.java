package net.linkedto.stackable.mixin;

import net.linkedto.stackable.Stackable;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class MixinItemStack {
    @Inject(method = "getMaxStackSize", at = @At("HEAD"), cancellable = true)
    private void stackable$onGetMaxStackSize(CallbackInfoReturnable<Integer> cir) {
        ItemStack self = (ItemStack) (Object) this;
        int configured = Stackable.getConfiguredSize(self);
        if (configured > 0) {
            cir.setReturnValue(configured);
        }
    }
}
