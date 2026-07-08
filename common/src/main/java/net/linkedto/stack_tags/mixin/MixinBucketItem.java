package net.linkedto.stack_tags.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(BucketItem.class)
public class MixinBucketItem {

    @Overwrite
    public static ItemStack getEmptySuccessItem(ItemStack stack, Player player) {
        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
            if (stack.isEmpty()) {
                return new ItemStack(Items.BUCKET);
            }
            if (!player.getInventory().add(new ItemStack(Items.BUCKET))) {
                player.drop(new ItemStack(Items.BUCKET), false);
            }
            return stack;
        }
        return stack;
    }
}
