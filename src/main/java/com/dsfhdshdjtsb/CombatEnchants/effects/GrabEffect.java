package com.dsfhdshdjtsb.CombatEnchants.effects;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;

public class GrabEffect extends StatusEffect {
    public GrabEffect() {
        super(StatusEffectCategory.HARMFUL, 0);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(entity.getWorld() instanceof ServerWorld)
        {
            ((ServerWorld) entity.getWorld()).spawnParticles(ParticleTypes.FIREWORK, entity.getX(), entity.getBodyY(0.5D) + 2.5, entity.getZ(), 1, 0.1, 0.1, 0.1, 0.0D);
        }
        if(amplifier > 1)
        {
            entity.removeStatusEffect(CombatEnchants.GRAB_EFFECT);
        }
        return super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration % 5 == 0;
    }
}
