package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

import java.util.Random;

public class ShieldingEnchantment extends Enchantment {
    public ShieldingEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.WEARABLE, CombatEnchants.ALL_ARMOR);
    }

    @Override
    public int getMinPower(int level) {
        return 1 + (level - 1) * 10;
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 15;
    }

    @Override
    public void onUserDamaged(LivingEntity user, Entity attacker, int level) {
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        if(other.equals(CombatEnchants.SORCERY))
            return false;
        return super.canAccept(other);
    }
}
