package com.dsfhdshdjtsb.CombatEnchants.mixin;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(BowItem.class)
public abstract class CenchantsBowItemMixin {

    //thank you to Rubydesic on the fabric mc discord for the help
    @ModifyVariable(at = @At("STORE"), method = "onStoppedUsing", ordinal = 0 )
    @SuppressWarnings("InvalidInjectorMethodSignature")
    public float onStoppedUsing(float value, ItemStack stack, World world, LivingEntity user)
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
        return value;
    }
}
