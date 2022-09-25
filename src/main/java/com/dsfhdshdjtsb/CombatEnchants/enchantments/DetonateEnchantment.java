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
import net.minecraft.item.BowItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.explosion.Explosion;

public class DetonateEnchantment extends Enchantment {
    public DetonateEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.CROSSBOW, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
        if(ModConfigs.DETONATE)
            Registry.register(Registry.ENCHANTMENT, new Identifier("cenchants", "detonate"), this);
    }

    @Override
    public int getMinPower(int level) {
        return 10;
    }

    @Override
    public int getMaxPower(int level) {
        return 60;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(target instanceof LivingEntity) {
            DamageSource damageSource = ((LivingEntity) target).getRecentDamageSource();
            if((damageSource != null && !damageSource.isProjectile()) || user.getMainHandStack().getItem() instanceof BowItem)
                return;
        }
        if(target instanceof LivingEntity) {
            Explosion explosion = user.world.createExplosion(target, target.getX(), target.getY(), target.getZ(), 1.5f, Explosion.DestructionType.BREAK);
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
