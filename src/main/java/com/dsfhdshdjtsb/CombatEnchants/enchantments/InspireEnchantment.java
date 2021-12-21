package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;

import java.util.List;

public class InspireEnchantment extends Enchantment {
    public InspireEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinPower(int level) {
        return 5 + (level - 1) * 8;
    }

    @Override
    public int getMaxPower(int level) {
        return super.getMinPower(level) + 50;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (EnchantmentHelper.getLevel(CombatEnchants.INSPIRE, user.getMainHandStack()) == 0 || target.distanceTo(user) >= 6)
            return;

        List<LivingEntity> list = user.world.getNonSpectatingEntities(LivingEntity.class, user.getBoundingBox()
                .expand(8, 0.25D, 8));

        boolean activated = false;
        for (LivingEntity e : list) {
            if(e instanceof TameableEntity && user.equals(((TameableEntity)(e)).getOwner()))
            {
                activated = true;
                switch (level) {
                    case 1 -> {
                        e.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 20, 0));
                    }
                    case 2 -> {
                        e.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 40, 0));
                        e.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 40, 0));
                    }
                    case 3 -> {
                        e.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 60, 0));
                        e.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 60, 0));
                        e.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 60, 0));
                    }
                    case 4 -> {
                        e.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 80, 0));
                        e.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 80, 0));
                        e.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 80, 0));
                        e.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 80, 0));
                    }
                    case 5 -> {
                        e.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0));
                        e.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 100, 1));
                        e.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 0));
                        e.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 100, 1));
                    }
                }
            }
        }
        if (activated && user.world instanceof ServerWorld) {
            for (double x = -(9); x <= (9); x = x + 1) {
                double y = Math.sqrt((9) * (9) - x * x);
                ((ServerWorld) user.world).spawnParticles(ParticleTypes.END_ROD, user.getX() + x, user.getBodyY(0.5D), user.getZ() + y, 0, 1, 0.0D, 1, 0.0D);
                ((ServerWorld) user.world).spawnParticles(ParticleTypes.END_ROD, user.getX() + x, user.getBodyY(0.5D), user.getZ() - y, 0, 1, 0.0D, 1, 0.0D);
            }
        }
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }
}
