package com.dsfhdshdjtsb.CombatEnchants.effects;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class FireWalkEffect extends StatusEffect {
    public FireWalkEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0);
    }


    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(entity.isOnGround())
        {
            World world = entity.getWorld();
            BlockPos pos = entity.getBlockPos();
            if(world.getBlockState(pos) == Blocks.AIR.getDefaultState()) {
                BlockState fire = Blocks.FIRE.getDefaultState();
                world.setBlockState(pos, fire);
                entity.setFireTicks(0);
            }
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
