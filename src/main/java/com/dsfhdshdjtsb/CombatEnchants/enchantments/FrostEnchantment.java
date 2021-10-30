package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;

public class FrostEnchantment extends Enchantment {
    public FrostEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.CROSSBOW, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
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
        //if(target.distanceTo(user) < 4)
            //return;
        if(target instanceof LivingEntity) {
            target.setFrozenTicks(155 + level * (81));
        }
        super.onTargetDamaged(user, target, level);
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        if(other.equals(CombatEnchants.TRANQUILIZE))
            return false;
        return super.canAccept(other);
    }
}
