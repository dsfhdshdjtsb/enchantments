package com.dsfhdshdjtsb.CombatEnchants.effects;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectType;

import java.util.Objects;

public class LifestealCooldwon extends StatusEffect {
    public LifestealCooldwon() {
        super(StatusEffectType.HARMFUL, 0x00000000);
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
