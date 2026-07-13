package net.linkedto.stack_config.mixin;

import net.linkedto.stack_config.StackConfig;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class MixinItemStack {
    @Inject(method = "getMaxStackSize", at = @At("HEAD"), cancellable = true)
    private void stackConfig$onGetMaxStackSize(CallbackInfoReturnable<Integer> cir) {
        ItemStack self = (ItemStack) (Object) this;
        int configured = StackConfig.getConfiguredSize(self);
        if (configured > 0) {
            cir.setReturnValue(configured);
        }
    }
}
