package net.linkedto.stack_tags.mixin;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SolidBucketItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SolidBucketItem.class)
public class MixinSolidBucketItem {

    @Redirect(
        method = "useOn",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/player/Player;setItemInHand(Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/item/ItemStack;)V"
        )
    )
    private void modifySetItemInHand(Player player, InteractionHand hand, ItemStack stack) {
        ItemStack handStack = player.getItemInHand(hand);
        if (handStack.isEmpty()) {
            player.setItemInHand(hand, new ItemStack(Items.BUCKET));
        } else if (!player.getInventory().add(new ItemStack(Items.BUCKET))) {
            player.drop(new ItemStack(Items.BUCKET), false);
        }
    }
}
