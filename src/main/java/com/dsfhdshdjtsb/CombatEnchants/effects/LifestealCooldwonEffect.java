package com.dsfhdshdjtsb.CombatEnchants.effects;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectType;

import java.awt.*;
import java.util.Objects;

public class LifestealCooldwonEffect extends StatusEffect {
    public LifestealCooldwonEffect() {
        super(StatusEffectType.HARMFUL, 0);
    }


    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(EnchantmentHelper.getLevel(CombatEnchants.LIFESTEAL, entity.getMainHandStack()) == 0) {
            int currentDuration = Objects.requireNonNull(entity.getStatusEffect(CombatEnchants.LIFESTEAL_COOLDOWN_EFFECT)).getDuration();
            entity.addStatusEffect(new StatusEffectInstance(CombatEnchants.LIFESTEAL_COOLDOWN_EFFECT, currentDuration + 20));
        }
        super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration % 20 == 0;
    }
}
