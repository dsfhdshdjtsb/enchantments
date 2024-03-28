package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import com.dsfhdshdjtsb.CombatEnchants.config.ModConfigs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;

import java.util.List;

public class RejuvenateEnchantment extends Enchantment {
    public RejuvenateEnchantment() {
        super(Enchantment.properties(ItemTags.BOW_ENCHANTABLE,
                2, 2, Enchantment.leveledCost(5, 20),
                Enchantment.leveledCost(50, 20), 8, EquipmentSlot.MAINHAND));        if(ModConfigs.REJUVENATE)
            Registry.register(Registries.ENCHANTMENT, new Identifier("cenchants", "rejuvenate"), this);
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(target instanceof LivingEntity) {
            DamageSource damageSource = ((LivingEntity) target).getRecentDamageSource();
            if(damageSource != null && !damageSource.getType().msgId().equals("arrow") || (user.getMainHandStack().getItem() instanceof CrossbowItem))
                return;
        }

        List<LivingEntity> list = user.getWorld().getNonSpectatingEntities(LivingEntity.class, user.getBoundingBox()
                .expand(8, 0.25D, 8));

        boolean activated = false;
        for (LivingEntity e : list) {
            if(e instanceof PlayerEntity || (e instanceof TameableEntity && user.equals(((TameableEntity)(e)).getOwner()))) {
                activated = true;
                switch (level) {
                    case 1 -> {
                        e.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 60, 0));
                        e.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 60, 0));
                    }
                    case 2 -> {
                        e.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 1));
                        e.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 100, 0));
                        e.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0));
                    }
                }
                if(e.getWorld() instanceof ServerWorld)
                {

                    //((ServerWorld) e.world).spawnParticles(CombatEnchants.SHIELD_PARTICLE, e.getX(), e.getBodyY(0.2D), e.getZ(), 1, 0.3D, 0.3D, 0.3D, 0.0D);
                    ((ServerWorld) e.getWorld()).spawnParticles(ParticleTypes.HEART, e.getX(), e.getBodyY(0.2D), e.getZ(), 1, 0.3D, 0.3D, 0.3D, 0.0D);
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
