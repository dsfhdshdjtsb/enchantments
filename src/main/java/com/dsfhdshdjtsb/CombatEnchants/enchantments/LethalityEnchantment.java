package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;

public class LethalityEnchantment extends Enchantment {
    public LethalityEnchantment() {
        super(Rarity.COMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinPower(int level) {
        return 1;
    }

    @Override
    public int getMaxPower(int level) {
        return level * 4;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(EnchantmentHelper.getLevel(CombatEnchants.LETHALITY, user.getMainHandStack()) == 0||target.distanceTo(user) >= 6)
            return;
        if (user instanceof PlayerEntity && target instanceof LivingEntity) {
            float bDamage = ((((LivingEntity) target).getArmor() - user.getArmor()) / (6.0f - level));
            float damage = (float)user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE) + EnchantmentHelper.getAttackDamage(user.getMainHandStack(), user.getGroup());

            if(bDamage > 0)
                target.damage(DamageSource.player((PlayerEntity) user), bDamage + damage);
        }
        super.onTargetDamaged(user, target, level);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        if(other.equals(CombatEnchants.PERCEPTION))
            return false;
        return super.canAccept(other);
    }
}
