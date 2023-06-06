package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import com.dsfhdshdjtsb.CombatEnchants.config.ModConfigs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;

public class RampageEnchantment extends Enchantment {

    public RampageEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        if(ModConfigs.RAMPAGE)
            Registry.register(Registries.ENCHANTMENT, new Identifier("cenchants", "rampage"), this);
    }

    @Override
    public int getMinPower(int level) {
        return 5 + (level - 1) * 8;
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 50;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(EnchantmentHelper.getLevel(CombatEnchants.RAMPAGE, user.getMainHandStack()) == 0||target.distanceTo(user) >= 6)
            return;
        if(target instanceof PlayerEntity)
        {
            if(((LivingEntity)target).isDead()) {
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 80 + level * 40, 1));
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 80 + level * 40, 1));
                user.addStatusEffect(new StatusEffectInstance(CombatEnchants.RAMPAGE_EFFECT, 80 + level * 40, 0));
                if (user.getWorld() instanceof ServerWorld) {
                    ((ServerWorld) target.getWorld()).spawnParticles(ParticleTypes.SOUL, target.getX(), target.getBodyY(0.5D), target.getZ(), 0, 1, 0.0D, 1, 0.0D);
                    ((ServerWorld) user.getWorld()).spawnParticles(ParticleTypes.ANGRY_VILLAGER, user.getX(), user.getBodyY(0.5D) + 0.5, user.getZ(), 2, 0.4, 0.0D, 0.4, 0.0D);
                }
                return;
            }
            if(user.getStatusEffect(CombatEnchants.RAMPAGE_EFFECT) != null)
            {
                ((LivingEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, level * 20, 0));
            }
        }

        if(target instanceof LivingEntity)
        {
            if(((LivingEntity)target).isDead()) {
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 80 + level * 40, 0));
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 80 + level * 40, 0));
                user.addStatusEffect(new StatusEffectInstance(CombatEnchants.RAMPAGE_EFFECT, 80 + level * 40, 0));
                if (user.getWorld() instanceof ServerWorld) {
                    ((ServerWorld) target.getWorld()).spawnParticles(ParticleTypes.SOUL, target.getX(), target.getBodyY(0.5D), target.getZ(), 0, 1, 0.0D, 1, 0.0D);
                    ((ServerWorld) user.getWorld()).spawnParticles(ParticleTypes.ANGRY_VILLAGER, user.getX(), user.getBodyY(0.5D) + 0.5, user.getZ(), 2, 0.4, 0.0D, 0.4, 0.0D);
                }
                return;
            }

            if(user.getStatusEffect(CombatEnchants.RAMPAGE_EFFECT) != null)
            {
                ((LivingEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, level * 20, 0));
            }
        }
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        if(other.equals(CombatEnchants.TRIUMPH))
            return false;
        return super.canAccept(other);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
