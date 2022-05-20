package com.dsfhdshdjtsb.CombatEnchants.effects;

import net.minecraft.entity.EntityPose;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;

public class SleepyEffect extends StatusEffect {
    public SleepyEffect() {
        super(StatusEffectCategory.HARMFUL, 0);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        //tbh dont know what this does but vanilla code does it liek this so whatever
        if (!(entity instanceof PlayerEntity))
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, amplifier * 20, 255, true, false));
        entity.setPose(EntityPose.SLEEPING);
        entity.setSleepingPosition(entity.getBlockPos());
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration == amplifier * 20;
    }
}
