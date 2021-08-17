package com.dsfhdshdjtsb.EnchantsPlus.enchantments;

import com.dsfhdshdjtsb.EnchantsPlus.EnchantsPlus;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;

import java.util.List;

public class LifestealEnchantment extends Enchantment {
    public LifestealEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
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
        if (EnchantmentHelper.getLevel(EnchantsPlus.LIFESTEAL, user.getMainHandStack()) == 0)
            return;
        if (user.getStatusEffect(EnchantsPlus.LIFESTEAL_COOLDOWN_EFFECT) == null) {
            List<LivingEntity> list = target.world.getNonSpectatingEntities(LivingEntity.class, target.getBoundingBox()
                    .expand(1 + level, 0.25D, 1 + level));
            int counter = 0;

            for (LivingEntity e : list) {
                if (!e.equals(user)) {
                    counter++;
                    e.damage(DamageSource.MAGIC, 1.0f);

                    if (target.world instanceof ServerWorld) {
                        double xdif = e.getX() - user.getX();
                        double ydif = e.getBodyY(0.5D) - user.getBodyY(0.5D);
                        double zdif = e.getZ() - user.getZ();

                        int particleNumConstant = 20;
                        double x = 0;
                        double y = 0;
                        double z = 0;
                        while(Math.abs(x) < Math.abs(xdif))
                        {
                            ((ServerWorld) target.world).spawnParticles(ParticleTypes.COMPOSTER, user.getX() + x,
                                    user.getBodyY(0.5D) + y, user.getZ() + z, 0, 1, 0.0D, 1, 0.0D);
                            x = x + xdif/particleNumConstant;
                            y = y + ydif/particleNumConstant;
                            z = z + zdif/particleNumConstant;
                        }
                    }
                }
            }
            if (target.world instanceof ServerWorld) {
                for (double x = -(2 + level); x <= (2 + level); x = x + 1) {
                    double y = Math.sqrt((2 + level) * (2 + level) - x * x);
                    ((ServerWorld) target.world).spawnParticles(ParticleTypes.COMPOSTER, target.getX() + x, target.getBodyY(0.5D), target.getZ() + y, 0, 1, 0.0D, 1, 0.0D);
                    ((ServerWorld) target.world).spawnParticles(ParticleTypes.COMPOSTER, target.getX() + x, target.getBodyY(0.5D), target.getZ() - y, 0, 1, 0.0D, 1, 0.0D);
                }
            }
            user.heal(counter + (level - 1));
            user.addStatusEffect(new StatusEffectInstance(EnchantsPlus.LIFESTEAL_COOLDOWN_EFFECT, 300 - (level * 20)));
            super.onTargetDamaged(user, target, level);
        }
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        if(other.equals(Enchantments.SHARPNESS) || other.equals(Enchantments.BANE_OF_ARTHROPODS) || other.equals(Enchantments.SMITE))
            return false;
        return super.canAccept(other);
    }
}
