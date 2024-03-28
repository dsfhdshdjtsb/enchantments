package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import com.dsfhdshdjtsb.CombatEnchants.config.ModConfigs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;

public class LethalityEnchantment extends Enchantment {
    public LethalityEnchantment() {
        super(Enchantment.properties(ItemTags.SWORD_ENCHANTABLE,
                10, 3, Enchantment.leveledCost(2, 2),
                Enchantment.leveledCost(4,2), 4, EquipmentSlot.MAINHAND));
        if(ModConfigs.LETHALITY)
            Registry.register(Registries.ENCHANTMENT, new Identifier("cenchants", "lethality"), this);
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(EnchantmentHelper.getLevel(CombatEnchants.LETHALITY, user.getMainHandStack()) == 0||target.distanceTo(user) >= 6)
            return;
        if (user instanceof PlayerEntity && target instanceof LivingEntity) {
            float bDamage = (float)Math.cbrt(level * (((LivingEntity) target).getArmor() - user.getArmor()));
            float damage = (float)user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE) + EnchantmentHelper.getAttackDamage(user.getMainHandStack(), null);

            if(bDamage > 0)
                target.damage(target.getWorld().getDamageSources().playerAttack((PlayerEntity) user), bDamage + damage);
        }
    }


    @Override
    protected boolean canAccept(Enchantment other) {
        if(other.equals(CombatEnchants.PERCEPTION))
            return false;
        return super.canAccept(other);
    }
}
