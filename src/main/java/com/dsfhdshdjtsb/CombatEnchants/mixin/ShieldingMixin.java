package com.dsfhdshdjtsb.CombatEnchants.mixin;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(LivingEntity.class)
public abstract class ShieldingMixin extends Entity {
    @Shadow @Final private DefaultedList<ItemStack> equippedArmor;

    @Shadow public abstract void setStatusEffect(StatusEffectInstance effect, @Nullable Entity source);

    @Shadow public abstract boolean hasStatusEffect(StatusEffect effect);

    @Shadow @Nullable public abstract StatusEffectInstance getStatusEffect(StatusEffect effect);

    @Shadow public abstract boolean removeStatusEffect(StatusEffect type);

    public ShieldingMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(at = @At("HEAD"), method = "tick")
    private void tick(CallbackInfo info){
        ItemStack helmet = equippedArmor.get(3);
        ItemStack chestplate = equippedArmor.get(2);
        ItemStack leggings = equippedArmor.get(1);
        ItemStack boots = equippedArmor.get(0);

        int shieldingLevel = 0;

        shieldingLevel += EnchantmentHelper.getLevel(CombatEnchants.SHEILDING, helmet);
        shieldingLevel += EnchantmentHelper.getLevel(CombatEnchants.SHEILDING, chestplate);
        shieldingLevel += EnchantmentHelper.getLevel(CombatEnchants.SHEILDING, leggings);
        shieldingLevel += EnchantmentHelper.getLevel(CombatEnchants.SHEILDING, boots);

        if(shieldingLevel > 0 && !this.hasStatusEffect(CombatEnchants.SHIELDING_COOLDOWN_EFFECT))
        {
            StatusEffectInstance absorption = this.getStatusEffect(StatusEffects.ABSORPTION);
            if(absorption != null && absorption.getAmplifier() > shieldingLevel / 4)
                this.removeStatusEffect(StatusEffects.ABSORPTION);
            this.setStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, shieldingLevel * 10, shieldingLevel / 4, false, false, true), null);
        }
    }
}
