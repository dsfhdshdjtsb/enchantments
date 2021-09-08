package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.world.explosion.Explosion;

public class SuicideEnchantment extends Enchantment {
    public SuicideEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinPower(int level) {
        return 1;
    }

    @Override
    public int getMaxPower(int level) {
        return 15;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(EnchantmentHelper.getLevel(CombatEnchants.SUICIDE, user.getMainHandStack()) == 0||target.distanceTo(user) >= 6)
            return;
        if(target instanceof LivingEntity)
        {
            Explosion explosion = user.world.createExplosion(user, user.getX(), user.getY(), user.getZ(), 2.0f, Explosion.DestructionType.BREAK);
            user.setVelocity(0, 0.5, 0);
            user.damage(DamageSource.explosion(explosion), 100);
            user.kill();
        }
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }
}
