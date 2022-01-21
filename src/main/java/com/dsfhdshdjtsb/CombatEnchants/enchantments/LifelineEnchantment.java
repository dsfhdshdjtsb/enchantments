package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import com.dsfhdshdjtsb.CombatEnchants.config.ModConfigs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.core.jmx.Server;

import java.util.List;

public class LifelineEnchantment extends Enchantment {
    public LifelineEnchantment() {
        super(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.ARMOR_CHEST, new EquipmentSlot[] {EquipmentSlot.CHEST});
        if(ModConfigs.LIFELINE)
            Registry.register(Registry.ENCHANTMENT, new Identifier("cenchants", "lifeline"), this);
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
        if(user.getHealth() <= 10.0f && !user.hasStatusEffect(CombatEnchants.LIFELINE_COOLDOWN_EFFECT))
        {
            int attackerCount = user.world.getNonSpectatingEntities(LivingEntity.class, user.getBoundingBox()
                    .expand(8.0D, 0.25D, 8.0D)).size() - 1;

            if(attackerCount >= 4)
                attackerCount = 4;

            user.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 40 + attackerCount * 20, attackerCount));
            user.addStatusEffect(new StatusEffectInstance(CombatEnchants.LIFELINE_COOLDOWN_EFFECT, 1200, 0));
            if(user.world instanceof ServerWorld)
            ((ServerWorld) user.world).spawnParticles(CombatEnchants.SHIELD_PARTICLE, user.getX(), user.getBodyY(0.5D), user.getZ(), 3, 0.3D, 0.3D, 0.3D, 0.0D);
        }
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }
}
