package com.dsfhdshdjtsb.CombatEnchants.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class LightningImmuneEffect extends StatusEffect {
    public LightningImmuneEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
