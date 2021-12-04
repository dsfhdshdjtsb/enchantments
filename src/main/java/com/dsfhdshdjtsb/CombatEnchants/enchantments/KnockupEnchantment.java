package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class KnockupEnchantment extends Enchantment {
    public KnockupEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentTarget.BOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinPower(int level) {
        return 12 + (level - 1) * 20;
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 25;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {

        if(target.distanceTo(user) < 5 && !user.equals(target))
            return;
        if(target instanceof LivingEntity)
        {
            target.addVelocity( 0, level / 5.0f, 0);
        }

    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }
}
