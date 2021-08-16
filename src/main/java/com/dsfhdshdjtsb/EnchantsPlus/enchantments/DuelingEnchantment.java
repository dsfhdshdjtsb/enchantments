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

public class DuelingEnchantment  extends Enchantment {
    public DuelingEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinPower(int level) {
        return level * 25;
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 50;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(EnchantmentHelper.getLevel(EnchantsPlus.DUELING, user.getMainHandStack()) == 0)
            return;
        List<LivingEntity> list = target.world.getNonSpectatingEntities(LivingEntity.class, target.getBoundingBox().expand(5.0D, 0.25D, 5.0D));
        boolean bl = false;
        for (LivingEntity e : list) {
            if (!e.equals(user) && !e.equals(target)) {
                bl = true;
                e.takeKnockback(1.5, target.getX() - e.getX(), target.getZ() - e.getZ());
            }
        }

        if (bl && target.world instanceof ServerWorld) {
            for (double x = -6; x <= 6; x = x + 1) {
                double y = Math.sqrt(36 - x * x);
                ((ServerWorld) target.world).spawnParticles(ParticleTypes.CLOUD, target.getX() + x, target.getBodyY(0.5D), target.getZ() + y, 0, 1, 0.0D, 1, 0.0D);
                ((ServerWorld) target.world).spawnParticles(ParticleTypes.CLOUD, target.getX() + x, target.getBodyY(0.5D), target.getZ() - y, 0, 1, 0.0D, 1, 0.0D);
            }
        }
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        if(other.equals(Enchantments.SWEEPING))
            return false;
        return super.canAccept(other);
    }
}
