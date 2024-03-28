package com.dsfhdshdjtsb.CombatEnchants.effects;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;

public class MarkEffect extends StatusEffect {
    public MarkEffect() {
        super(StatusEffectCategory.HARMFUL, 0);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        //onTargetDamaged ran twice, so this whole file is a workaround sort of
        entity.removeStatusEffect(CombatEnchants.MARK_EFFECT);
        entity.addStatusEffect(new StatusEffectInstance(CombatEnchants.MARK_EFFECT, amplifier * 20, 0));
        return super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration <= 5 && amplifier != 0;
    }
}
