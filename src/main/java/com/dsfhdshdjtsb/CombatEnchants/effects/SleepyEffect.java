package com.dsfhdshdjtsb.CombatEnchants.effects;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

public class SleepyEffect extends StatusEffect {
    public SleepyEffect() {
        super(StatusEffectType.HARMFUL, 0x00808080);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        //tbh dont know what this does but vanilla code does it liek this so whatever
        if (!(entity instanceof PlayerEntity))
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, amplifier * 20, 255));
        entity.setPose(EntityPose.SLEEPING);
        entity.setSleepingPosition(entity.getBlockPos());
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration == amplifier * 20;
    }
}
