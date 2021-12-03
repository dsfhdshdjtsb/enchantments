package com.dsfhdshdjtsb.CombatEnchants.effects;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import com.dsfhdshdjtsb.CombatEnchants.enchantments.FervorEnchantment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;


public class FervorEffect extends StatusEffect {

    public FervorEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0);
    }


    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {

    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration <= 1;
    }
}
