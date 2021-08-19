package com.dsfhdshdjtsb.EnchantsPlus.enchantments;

import com.dsfhdshdjtsb.EnchantsPlus.EnchantsPlus;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

import java.util.Objects;

public class HunterEnchantment extends Enchantment {
    public HunterEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.BOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinPower(int level) {
        return 25;
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 50;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(target instanceof LivingEntity) {
            if(((LivingEntity) target).getStatusEffect(EnchantsPlus.MARK_EFFECT) == null)
            {
                ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(EnchantsPlus.MARK_EFFECT, 1, 5));
                ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 100, 0));
            }
            else if(Objects.requireNonNull(((LivingEntity) target).getStatusEffect(EnchantsPlus.MARK_EFFECT)).getAmplifier() == 0)
            {
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, level * 40, 1));
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, level * 40, 1));
            }
        }

        super.onTargetDamaged(user, target, level);
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }
}
