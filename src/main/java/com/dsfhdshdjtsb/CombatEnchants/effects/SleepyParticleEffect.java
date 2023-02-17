package com.dsfhdshdjtsb.CombatEnchants.effects;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;

public class SleepyParticleEffect extends StatusEffect {
    public SleepyParticleEffect() {
        super(StatusEffectCategory.HARMFUL, 0x000000);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(entity.world instanceof ServerWorld)
        {
            //((ServerWorld) entity.world).spawnParticles(CombatEnchants.SLEEPY_PARTICLE, entity.getX(), entity.getBodyY(0.5D) + 0.5, entity.getZ(), 1, 0.2, 0.2, 0.2, 0.0D);
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration % 6 == 0;
    }
}
