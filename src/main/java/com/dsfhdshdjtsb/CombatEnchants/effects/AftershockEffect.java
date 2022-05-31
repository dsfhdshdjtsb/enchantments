package com.dsfhdshdjtsb.CombatEnchants.effects;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;

import java.util.List;

public class AftershockEffect extends StatusEffect {

    public AftershockEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0);
    }


    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.damage(DamageSource.MAGIC, ((amplifier+1) / 2.0f));
        entity.addStatusEffect(new StatusEffectInstance(CombatEnchants.DELAYED_DEATH_EFFECT, 17, amplifier, true, false));
        System.out.println(amplifier);
        if (entity.world instanceof ServerWorld) {
            ((ServerWorld) entity.world).spawnParticles(ParticleTypes.SONIC_BOOM, entity.getX(), entity.getBodyY(0.5D), entity.getZ(), 1, 0, 0, 0, 0.0D);
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration <= 1;
    }
}
