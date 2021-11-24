package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;

import java.util.ArrayList;
import java.util.List;

public class ZapEnchantment extends Enchantment {
    public ZapEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentTarget.BOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinPower(int level) {
        return 10 + 20 * (level - 1);
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 50;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(target.distanceTo(user) < 4)
            return;
        List<LivingEntity> hit = new ArrayList<>();
        if(target instanceof LivingEntity) {
            hit.add((LivingEntity) target);
            helper((LivingEntity) target, level, hit);
        }
        super.onTargetDamaged(user, target, level);
    }

    private void helper(LivingEntity source, int level, List<LivingEntity> hit)
    {
        System.out.println(source.getHealth());
        if(!hit.contains(source))
            source.damage(DamageSource.MAGIC, level + 1.0f);
        System.out.println(source.getHealth());
        hit.add(source);

        List<LivingEntity> list = source.world.getNonSpectatingEntities(LivingEntity.class, source.getBoundingBox()
                .expand(1.0D + 3, 0.25D, 1.0D + 3));
        for(LivingEntity e: list)
        {
            if(!e.equals(source) && !hit.contains(e)){
                helper(e, level, hit);
                if (source.world instanceof ServerWorld) {
                    double xdif = e.getX() - source.getX();
                    double ydif = e.getBodyY(0.5D) - source.getBodyY(0.5D);
                    double zdif = e.getZ() - source.getZ();

                    int particleNumConstant = 20; //number of particles
                    double x = 0;
                    double y = 0;
                    double z = 0;
                    while (Math.abs(x) < Math.abs(xdif)) {
                        ((ServerWorld) source.world).spawnParticles(ParticleTypes.ELECTRIC_SPARK, source.getX() + x,
                                source.getBodyY(0.5D) + y, source.getZ() + z, 0, 1, 0.0D, 1, 0.0D);
                        x = x + xdif / particleNumConstant;
                        y = y + ydif / particleNumConstant;
                        z = z + zdif / particleNumConstant;
                    }
                }
            }
        }
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        if(other.equals(CombatEnchants.VOLLEY))
            return false;
        return super.canAccept(other);
    }
}
