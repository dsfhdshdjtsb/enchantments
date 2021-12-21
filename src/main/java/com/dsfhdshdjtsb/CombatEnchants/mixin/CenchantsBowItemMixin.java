package com.dsfhdshdjtsb.CombatEnchants.mixin;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BowItem.class)
public abstract class CenchantsBowItemMixin {

    @Shadow public abstract TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand);

    //thank you to Rubydesic on the fabric mc discord for the help
    @Redirect(at = @At(value="INVOKE", target = "Lnet/minecraft/item/BowItem;getPullProgress(I)F"), method = "onStoppedUsing"  )
    public float onStoppedUsing(int useTicks, ItemStack stack, World world, LivingEntity user, int remainingUseTicks)
    {
        StatusEffectInstance barrageEffectInstance = user.getStatusEffect(CombatEnchants.BARRAGE_EFFECT);
        if(barrageEffectInstance != null)
        {
            if(barrageEffectInstance.getDuration() >= 20)
            {
                user.removeStatusEffect(CombatEnchants.BARRAGE_EFFECT);
                user.addStatusEffect(new StatusEffectInstance(CombatEnchants.BARRAGE_EFFECT, 20));
            }
            return 1;
        }
        return BowItem.getPullProgress(useTicks);
    }
}
