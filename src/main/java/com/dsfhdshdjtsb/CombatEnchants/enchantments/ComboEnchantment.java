package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;


public class ComboEnchantment extends Enchantment {
    public ComboEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinPower(int level) {
        return 5 + (level - 1) * 8;
    }

    @Override
    public int getMaxPower(int level) {
        return super.getMinPower(level) + 50;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (EnchantmentHelper.getLevel(CombatEnchants.COMBO, user.getMainHandStack()) == 0||target.distanceTo(user) >= 6)
            return;
        if(target instanceof LivingEntity && user.world instanceof ServerWorld)
        {
            StatusEffectInstance mark = ((LivingEntity) target).getStatusEffect(CombatEnchants.MARK_EFFECT);
            if(mark != null && mark.getAmplifier() > 10 - level)
            {
                World world = target.world;
                LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(world);
                assert lightning != null;
                lightning.setPos(target.getX(), target.getY(), target.getZ());
                world.spawnEntity(lightning);
                ((LivingEntity) target).removeStatusEffect(CombatEnchants.MARK_EFFECT);
            }
        }

    }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }
}

