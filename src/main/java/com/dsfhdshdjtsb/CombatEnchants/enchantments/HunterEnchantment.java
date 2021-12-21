package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

import java.util.Objects;

public class HunterEnchantment extends Enchantment {
    public HunterEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.BOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
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
        if(target instanceof LivingEntity) {
            DamageSource damageSource = ((LivingEntity) target).getRecentDamageSource();
            if(damageSource != null && !damageSource.isProjectile())
                return;
        }
        if(target instanceof LivingEntity) {
            if(((LivingEntity) target).getStatusEffect(CombatEnchants.MARK_EFFECT) == null)
            {
                ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(CombatEnchants.MARK_EFFECT, 1, 5));
                ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 100, 0));
            }
            else if(Objects.requireNonNull(((LivingEntity) target).getStatusEffect(CombatEnchants.MARK_EFFECT)).getAmplifier() == 0)
            {
                ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(CombatEnchants.MARK_EFFECT, 60, 0));
                ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 60, 0));
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 150 * level, 1));
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 150 * level, 1));
            }
        }

        super.onTargetDamaged(user, target, level);
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
