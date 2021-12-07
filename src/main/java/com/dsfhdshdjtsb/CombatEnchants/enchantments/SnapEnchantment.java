package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.EvokerFangsEntity;
import net.minecraft.world.World;

import java.util.UUID;

public class SnapEnchantment extends Enchantment {
    public SnapEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.TRIDENT, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {

    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        if(other.equals(Enchantments.RIPTIDE))
            return false;
        return super.canAccept(other);
    }
}
