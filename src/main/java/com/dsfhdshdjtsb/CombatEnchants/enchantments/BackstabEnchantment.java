package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import com.dsfhdshdjtsb.CombatEnchants.config.ModConfigs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BackstabEnchantment extends Enchantment {
    public BackstabEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        if (ModConfigs.BACKSTAB)
            Registry.register(Registry.ENCHANTMENT, new Identifier("cenchants", "backstab"), this);
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
        if (EnchantmentHelper.getLevel(CombatEnchants.BACKSTAB, user.getMainHandStack()) == 0 || target.distanceTo(user) >= 6)
            return;
        float userYaw = user.headYaw;
        if (target instanceof LivingEntity) {
            float entityYaw = ((LivingEntity) target).headYaw;
            while (entityYaw > 180) {
                entityYaw -= 360;
            }
            float yawDiff = Math.abs(userYaw - entityYaw);
            if (yawDiff < 100) {
                float damage = (float)user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE) + EnchantmentHelper.getAttackDamage(user.getMainHandStack(), user.getGroup());
                target.damage(DamageSource.player((PlayerEntity) user), damage+2+((float)level));
                if (target.world instanceof ServerWorld) {
                    ((ServerWorld) user.world).spawnParticles(ParticleTypes.ENCHANTED_HIT, target.getX(), target.getBodyY(0.5D), target.getZ(), 20, 0.3, 0.5, 0.3, 0.5D);
                }
            }
        }

    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }
}