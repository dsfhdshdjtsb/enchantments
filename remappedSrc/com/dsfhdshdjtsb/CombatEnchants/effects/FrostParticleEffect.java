package com.dsfhdshdjtsb.CombatEnchants.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;

public class FrostParticleEffect extends StatusEffect {
    public FrostParticleEffect() {
        super(StatusEffectCategory.HARMFUL, 0);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(entity.world instanceof ServerWorld)
        {
            ((ServerWorld) entity.world).spawnParticles(ParticleTypes.SNOWFLAKE, entity.getX(), entity.getBodyY(0.5D) - 1, entity.getZ(), 1, 0.2, 0.2, 0.2, 0.0D);
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}