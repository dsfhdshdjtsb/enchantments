package com.dsfhdshdjtsb.EnchantsPlus.enchantments;

import com.dsfhdshdjtsb.EnchantsPlus.EnchantsPlus;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

public class TriumphEnchantment extends Enchantment {
    public TriumphEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinPower(int level) {
        return 5 + (level - 1) * 8;
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 50;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(EnchantmentHelper.getLevel(EnchantsPlus.TRIUMPH, user.getMainHandStack()) == 0)
            return;
        if(target instanceof PlayerEntity && ((LivingEntity)target).isDead())
        {
            user.heal(level);
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 1, (level * 3) - 1 ));
        }
        else if(target instanceof LivingEntity && ((LivingEntity)target).isDead())
        {
            user.heal(level / 2.0f);
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 1, 0));
        }
        super.onTargetDamaged(user, target, level);
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        if(other.equals(EnchantsPlus.RAMPAGE))
            return false;
        return super.canAccept(other);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
