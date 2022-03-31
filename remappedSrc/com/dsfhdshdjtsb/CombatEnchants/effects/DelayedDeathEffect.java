package com.dsfhdshdjtsb.CombatEnchants.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class DelayedDeathEffect extends StatusEffect {
    public DelayedDeathEffect() {
        super(StatusEffectCategory.HARMFUL, 0);
    }


    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.kill();
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration < 10;
    }
}
