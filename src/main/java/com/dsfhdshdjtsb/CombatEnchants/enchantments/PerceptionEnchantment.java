package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import com.dsfhdshdjtsb.CombatEnchants.config.ModConfigs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;


public class PerceptionEnchantment extends Enchantment {
    public PerceptionEnchantment() {
        super(Enchantment.properties(ItemTags.SWORD_ENCHANTABLE,
                2, 1, Enchantment.constantCost(20),
                Enchantment.constantCost(50), 8, EquipmentSlot.MAINHAND));        if(ModConfigs.PERCEPTION)
            Registry.register(Registries.ENCHANTMENT, new Identifier("cenchants", "perception"), this);
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (EnchantmentHelper.getLevel(CombatEnchants.PERCEPTION, user.getMainHandStack()) == 0||target.distanceTo(user) >= 6)
            return;
        if(user instanceof PlayerEntity && target instanceof LivingEntity)
        {

            float damage = (float)user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE) + EnchantmentHelper.getAttackDamage(user.getMainHandStack(), null);
            int exp = ((PlayerEntity) user).experienceLevel;
            if(exp < 3)
                exp = 3;
            target.damage(target.getWorld().getDamageSources().playerAttack((PlayerEntity) user), damage + (int)(Math.log(exp)) - 1);
        }
    }

    @Override
    public boolean isTreasure() {
        return true;
    }


    @Override
    protected boolean canAccept(Enchantment other) {
        if(other.equals(CombatEnchants.LETHALITY))
            return false;
        return super.canAccept(other);
    }
}
