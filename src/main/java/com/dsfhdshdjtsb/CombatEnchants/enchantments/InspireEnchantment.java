package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import com.dsfhdshdjtsb.CombatEnchants.config.ModConfigs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;

import java.util.List;

public class InspireEnchantment extends Enchantment {
    public InspireEnchantment() {
        super(Enchantment.properties(ItemTags.SWORD_ENCHANTABLE,
                3, 5, Enchantment.leveledCost(1, 11),
                Enchantment.leveledCost(21, 11), 1, EquipmentSlot.MAINHAND));
        if(ModConfigs.INSPIRE)
            Registry.register(Registries.ENCHANTMENT, new Identifier("cenchants", "inspire"), this);
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (EnchantmentHelper.getLevel(CombatEnchants.INSPIRE, user.getMainHandStack()) == 0 || target.distanceTo(user) >= 6)
            return;

        List<LivingEntity> list = user.getWorld().getNonSpectatingEntities(LivingEntity.class, user.getBoundingBox()
                .expand(8, 0.25D, 8));

        boolean activated = false;
        for (LivingEntity e : list) {
            if(e instanceof TameableEntity && user.equals(((TameableEntity)(e)).getOwner()))
            {
                activated = true;
                switch (level) {
                    case 1 -> {
                        e.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 20, 0));
                    }
                    case 2 -> {
                        e.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 40, 0));
                        e.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 40, 0));
                    }
                    case 3 -> {
                        e.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 60, 0));
                        e.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 60, 0));
                        e.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 60, 0));
                    }
                    case 4 -> {
                        e.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 80, 0));
                        e.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 80, 0));
                        e.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 80, 0));
                        e.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 80, 0));
                    }
                    case 5 -> {
                        e.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0));
                        e.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 100, 1));
                        e.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 0));
                        e.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 100, 1));
                    }
                }
            }
        }
        if (activated && user.getWorld() instanceof ServerWorld) {
            for (double x = -(9); x <= (9); x = x + 1) {
                double y = Math.sqrt((9) * (9) - x * x);
                ((ServerWorld) user.getWorld()).spawnParticles(ParticleTypes.END_ROD, user.getX() + x, user.getBodyY(0.5D), user.getZ() + y, 0, 1, 0.0D, 1, 0.0D);
                ((ServerWorld) user.getWorld()).spawnParticles(ParticleTypes.END_ROD, user.getX() + x, user.getBodyY(0.5D), user.getZ() - y, 0, 1, 0.0D, 1, 0.0D);
            }
        }
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }
}
