package com.dsfhdshdjtsb.EnchantsPlus.enchantments;

import com.dsfhdshdjtsb.EnchantsPlus.EnchantsPlus;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;

import java.util.Objects;

public class RampageEnchantment extends Enchantment {

    public RampageEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
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
        if(EnchantmentHelper.getLevel(EnchantsPlus.RAMPAGE, user.getMainHandStack()) == 0)
            return;
        if(target instanceof PlayerEntity)
        {
            if(((LivingEntity)target).isDead()) {
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 1));
                user.addStatusEffect(new StatusEffectInstance(EnchantsPlus.RAMPAGE_EFFECT, 200, 1));
                super.onTargetDamaged(user, target, level);
                return;
            }
            if(user.getStatusEffect(EnchantsPlus.RAMPAGE_EFFECT) != null)
            {
                ((LivingEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 60, 0));
            }
        }

        if(target instanceof LivingEntity)
        {
            if(((LivingEntity)target).isDead()) {
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 60, 0));
                user.addStatusEffect(new StatusEffectInstance(EnchantsPlus.RAMPAGE_EFFECT, 60, 1));
                super.onTargetDamaged(user, target, level);
                return;
            }
            if(user.getStatusEffect(EnchantsPlus.RAMPAGE_EFFECT) != null)
            {
                ((LivingEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 60, 0));
            }
        }
        super.onTargetDamaged(user, target, level);
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        if(other.equals(EnchantsPlus.TRIUMPH))
            return false;
        return super.canAccept(other);
    }
}
