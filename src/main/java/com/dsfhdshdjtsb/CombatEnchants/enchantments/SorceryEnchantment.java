package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;

import java.util.Random;

public class SorceryEnchantment extends Enchantment {
    public SorceryEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.WEARABLE, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
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
    public void onUserDamaged(LivingEntity user, Entity attacker, int level) {
        StatusEffect[] effects = {
                StatusEffects.ABSORPTION,
                StatusEffects.RESISTANCE,
                StatusEffects.FIRE_RESISTANCE,
                StatusEffects.HEALTH_BOOST,
                StatusEffects.JUMP_BOOST,
                StatusEffects.SPEED,
                StatusEffects.REGENERATION,
        };

        Random rand = new Random();

        if(rand.nextInt(4) == 0) {
            StatusEffect randEffect = effects[rand.nextInt(7)];
            int duration = level * 3;
            if (user.getStatusEffect(randEffect) == null)
                user.addStatusEffect(new StatusEffectInstance(randEffect, duration * 20, 0));
            else
                user.addStatusEffect(new StatusEffectInstance(randEffect, duration * 20, 1));
            super.onUserDamaged(user, attacker, level);
        }
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        if(other.equals(Enchantments.PROTECTION) || other.equals(Enchantments.FIRE_PROTECTION) ||
                other.equals(Enchantments.BLAST_PROTECTION) ||other.equals(Enchantments.PROJECTILE_PROTECTION))
            return false;
        return super.canAccept(other);
    }
}
