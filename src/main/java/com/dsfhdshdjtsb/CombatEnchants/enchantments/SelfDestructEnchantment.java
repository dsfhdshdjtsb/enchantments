package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import com.dsfhdshdjtsb.CombatEnchants.config.ModConfigs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class SelfDestructEnchantment extends Enchantment {
    public SelfDestructEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        if(ModConfigs.SELFDESTRUCT)
            Registry.register(Registries.ENCHANTMENT, new Identifier("cenchants", "self_destruct"), this);
    }

    @Override
    public int getMinPower(int level) {
        return 1;
    }

    @Override
    public int getMaxPower(int level) {
        return 10;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(EnchantmentHelper.getLevel(CombatEnchants.SELFDESTRUCT, user.getMainHandStack()) == 0||target.distanceTo(user) >= 6)
            return;
        if(!(user instanceof PlayerEntity) && !user.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING))
            return;
        if(target instanceof LivingEntity)
        {
            Explosion explosion = user.world.createExplosion(user, user.getX(), user.getY(), user.getZ(), 2.0f, World.ExplosionSourceType.TNT);
            user.setVelocity(0, 0.5, 0);
            user.damage(target.world.getDamageSources().magic(), 999);

        }
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }
}
