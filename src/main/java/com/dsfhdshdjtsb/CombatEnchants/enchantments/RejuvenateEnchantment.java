package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import com.dsfhdshdjtsb.CombatEnchants.config.ModConfigs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
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
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;

import java.util.List;

public class RejuvenateEnchantment extends Enchantment {
    public RejuvenateEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.BOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        if(ModConfigs.REJUVENATE)
            Registry.register(Registries.ENCHANTMENT, new Identifier("cenchants", "rejuvenate"), this);
    }

    @Override
    public int getMinPower(int level) {
        return 10 + 20 * (level - 1);
    }

    @Override
    public int getMaxPower(int level) {
        return this.getMinPower(level) + 50;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(target instanceof LivingEntity) {
            DamageSource damageSource = ((LivingEntity) target).getRecentDamageSource();
            if((damageSource != null && !damageSource.isProjectile()) || user.getMainHandStack().getItem() instanceof CrossbowItem)
                return;
        }

        List<LivingEntity> list = user.world.getNonSpectatingEntities(LivingEntity.class, user.getBoundingBox()
                .expand(8, 0.25D, 8));

        boolean activated = false;
        for (LivingEntity e : list) {
            System.out.println(e.getHeight());
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
                if(e.world instanceof ServerWorld)
                {

                    //((ServerWorld) e.world).spawnParticles(CombatEnchants.SHIELD_PARTICLE, e.getX(), e.getBodyY(0.2D), e.getZ(), 1, 0.3D, 0.3D, 0.3D, 0.0D);
                    ((ServerWorld) e.world).spawnParticles(ParticleTypes.HEART, e.getX(), e.getBodyY(0.2D), e.getZ(), 1, 0.3D, 0.3D, 0.3D, 0.0D);
                }
            }
        }

        if (activated && user.world instanceof ServerWorld) {
            for (double x = -(9); x <= (9); x = x + 1) {
                double y = Math.sqrt((9) * (9) - x * x);
                ((ServerWorld) user.world).spawnParticles(ParticleTypes.END_ROD, user.getX() + x, user.getBodyY(0.5D), user.getZ() + y, 0, 1, 0.0D, 1, 0.0D);
                ((ServerWorld) user.world).spawnParticles(ParticleTypes.END_ROD, user.getX() + x, user.getBodyY(0.5D), user.getZ() - y, 0, 1, 0.0D, 1, 0.0D);
            }
        }
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }
}
