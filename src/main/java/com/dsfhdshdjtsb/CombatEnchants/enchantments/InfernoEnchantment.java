package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import com.dsfhdshdjtsb.CombatEnchants.config.ModConfigs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;

import java.util.List;

public class InfernoEnchantment extends Enchantment {
    public InfernoEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        if(ModConfigs.INFERNO)
            Registry.register(Registries.ENCHANTMENT, new Identifier("cenchants", "inferno"), this);
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
        if(EnchantmentHelper.getLevel(CombatEnchants.INFERNO, user.getMainHandStack()) == 0||target.distanceTo(user) >= 6)
            return;
        List<LivingEntity> list = target.getWorld().getNonSpectatingEntities(LivingEntity.class, target.getBoundingBox()
                .expand(1.0D + level * 2, 0.25D, 1.0D + level * 2));

        for (LivingEntity e : list) {
            if (!e.equals(user)) {
                e.setFireTicks((level + 1) * 20);
            }
        }

        if (target.getWorld() instanceof ServerWorld && list.size() > 2) {
            ((ServerWorld) target.getWorld()).spawnParticles(ParticleTypes.FLAME, target.getX(), target.getBodyY(0.5D), target.getZ(), 7, 0.0D, 0.5D, 0.0D, 0.4D);

        }
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
