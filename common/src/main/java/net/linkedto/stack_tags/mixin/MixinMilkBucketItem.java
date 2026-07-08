package net.linkedto.stack_tags.mixin;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MilkBucketItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(MilkBucketItem.class)
public class MixinMilkBucketItem {

    @Overwrite
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        MilkBucketItem self = (MilkBucketItem) (Object) this;
        if (entity instanceof ServerPlayer player) {
            CriteriaTriggers.CONSUME_ITEM.trigger(player, stack);
            player.awardStat(Stats.ITEM_USED.get(self));
        }
        if (entity instanceof Player player && !player.getAbilities().instabuild) {
            stack.shrink(1);
        }
        if (!level.isClientSide) {
            entity.removeAllEffects();
        }
        if (stack.isEmpty()) {
            return new ItemStack(Items.BUCKET);
        }
        if (entity instanceof Player player && !player.getAbilities().instabuild) {
            if (!player.getInventory().add(new ItemStack(Items.BUCKET))) {
                player.drop(new ItemStack(Items.BUCKET), false);
            }
        }
        return stack;
    }
}
