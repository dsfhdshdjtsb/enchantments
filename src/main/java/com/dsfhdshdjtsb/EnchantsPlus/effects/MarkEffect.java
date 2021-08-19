package com.dsfhdshdjtsb.EnchantsPlus.effects;

import com.dsfhdshdjtsb.EnchantsPlus.EnchantsPlus;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectType;

public class MarkEffect extends StatusEffect {
    public MarkEffect() {
        super(StatusEffectType.HARMFUL, 0x00FFFFFF);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        //onTargetDamaged ran twice, so this whole file is a workaround sort of
        entity.removeStatusEffect(EnchantsPlus.MARK_EFFECT);
        entity.addStatusEffect(new StatusEffectInstance(EnchantsPlus.MARK_EFFECT, amplifier * 20, 0));
        super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration <= 5 && amplifier != 0;
    }
}
