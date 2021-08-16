package com.dsfhdshdjtsb.EnchantsPlus.enchantments;

import com.dsfhdshdjtsb.EnchantsPlus.EnchantsPlus;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;

import java.util.List;

public class InfernoEnchantment extends Enchantment {
    public InfernoEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinPower(int level) {
        return 10 + 20 * (level - 1);
    }

    @Override
    public int getMaxPower(int level) {
        return super.getMinPower(level) + 50;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(EnchantmentHelper.getLevel(EnchantsPlus.INFERNO, user.getMainHandStack()) == 0)
            return;
        List<LivingEntity> list = target.world.getNonSpectatingEntities(LivingEntity.class, target.getBoundingBox()
                .expand(1.0D + level * 2, 0.25D, 1.0D + level * 2));

        for (LivingEntity e : list) {
            if (!e.equals(user)) {
                e.setFireTicks((level + 1) * 20);
            }
        }

        if (target.world instanceof ServerWorld) {
            for (double x = -(2.0D + level * 2); x <= (2.0D + level * 2); x = x + 1) {
                double y = Math.sqrt((2.0D + level * 2) * (2.0D + level * 2) - x * x);
                ((ServerWorld) target.world).spawnParticles(ParticleTypes.FLAME, target.getX() + x, target.getBodyY(0.5D), target.getZ() + y, 0, 1, 0.0D, 1, 0.0D);
                ((ServerWorld) target.world).spawnParticles(ParticleTypes.FLAME, target.getX() + x, target.getBodyY(0.5D), target.getZ() - y, 0, 1, 0.0D, 1, 0.0D);
            }
        }
        super.onTargetDamaged(user, target, level);
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        if(other.equals(Enchantments.FIRE_ASPECT))
            return false;
        return super.canAccept(other);
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }
}
