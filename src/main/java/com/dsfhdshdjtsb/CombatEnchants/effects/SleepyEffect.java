package com.dsfhdshdjtsb.CombatEnchants.effects;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.util.math.Vec3d;

public class SleepyEffect extends StatusEffect {
    public SleepyEffect() {
        super(StatusEffectType.HARMFUL, 0x00FFFFFF);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        //tbh dont know what this does but vanilla code does it liek this so whatever
        entity.setPose(EntityPose.SLEEPING);
        entity.setVelocity(Vec3d.ZERO);
        entity.setSleepingPosition(entity.getBlockPos());
        entity.velocityDirty = true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration == amplifier * 20;
    }
}
