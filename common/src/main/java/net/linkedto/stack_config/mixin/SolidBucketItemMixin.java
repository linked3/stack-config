package net.linkedto.stack_config.mixin;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SolidBucketItem;
import net.minecraft.world.item.context.UseOnContext;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SolidBucketItem.class)
public class SolidBucketItemMixin {

    @Inject(method = "useOn", at = @At("RETURN"))
    private void stackConfig$fixStackLoss(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        InteractionResult result = cir.getReturnValue();
        if (!result.consumesAction()) return;
        Player player = context.getPlayer();
        if (player == null || player.hasInfiniteMaterials()) return;

        ItemStack remaining = context.getItemInHand();
        if (!remaining.isEmpty()) {
            player.setItemInHand(context.getHand(), remaining);
            ItemStack emptyBucket = Items.BUCKET.getDefaultInstance();
            if (!player.getInventory().add(emptyBucket)) {
                player.drop(emptyBucket, false);
            }
        }
    }
}
