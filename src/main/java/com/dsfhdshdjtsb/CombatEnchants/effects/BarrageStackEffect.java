package com.dsfhdshdjtsb.CombatEnchants.effects;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;


public class BarrageStackEffect extends StatusEffect {
    public BarrageStackEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(amplifier >= 9 && amplifier <= 15) {
            entity.removeStatusEffect(CombatEnchants.BARRAGE_STACK_EFFECT);
            entity.addStatusEffect(new StatusEffectInstance(CombatEnchants.BARRAGE_STACK_EFFECT, 200, amplifier - 10));
        }
        else if (amplifier >= 16)
        {
            entity.removeStatusEffect(CombatEnchants.BARRAGE_STACK_EFFECT);
            entity.addStatusEffect(new StatusEffectInstance(CombatEnchants.BARRAGE_EFFECT, 600, 0));
        }
        super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration <= 2;
    }
}
