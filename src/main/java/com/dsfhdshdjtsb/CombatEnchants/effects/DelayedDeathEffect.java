package com.dsfhdshdjtsb.CombatEnchants.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class DelayedDeathEffect extends StatusEffect {
    public DelayedDeathEffect() {
        super(StatusEffectCategory.HARMFUL, 0);
    }


    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.damage(entity.getDamageSources().magic(), ((amplifier+1) / 2.0f));
        return super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration < 10;
    }
}
