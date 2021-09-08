package com.dsfhdshdjtsb.CombatEnchants.effects;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.world.World;

import java.util.Objects;

public class FireWalkEffect extends StatusEffect {
    public FireWalkEffect() {
        super(StatusEffectType.BENEFICIAL, 0);
    }


    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(entity.isOnGround())
        {
            World world = entity.world;
            if(world.getBlockState(entity.getBlockPos()) == Blocks.AIR.getDefaultState()) {
                BlockState fire = Blocks.FIRE.getDefaultState();
                world.setBlockState(entity.getBlockPos(), fire);
                entity.setFireTicks(0);
            }
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
