package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import com.dsfhdshdjtsb.CombatEnchants.config.ModConfigs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

public class SelfDestructEnchantment extends Enchantment {
    public SelfDestructEnchantment() {
        super(Enchantment.properties(ItemTags.SWORD_ENCHANTABLE,
                5, 1, Enchantment.constantCost(20),
                Enchantment.constantCost(10), 1, EquipmentSlot.MAINHAND));
        if(ModConfigs.SELFDESTRUCT)
            Registry.register(Registries.ENCHANTMENT, new Identifier("cenchants", "self_destruct"), this);
    }


    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(EnchantmentHelper.getLevel(CombatEnchants.SELFDESTRUCT, user.getMainHandStack()) == 0||target.distanceTo(user) >= 6)
            return;
        if(!(user instanceof PlayerEntity) && !user.getWorld().getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING))
            return;
        if(target instanceof LivingEntity)
        {
            Explosion explosion = user.getWorld().createExplosion(user, user.getX(), user.getY(), user.getZ(), 2.0f, World.ExplosionSourceType.TNT);
            user.setVelocity(0, 0.5, 0);
            user.damage(target.getWorld().getDamageSources().magic(), 999);

        }
    }

}
