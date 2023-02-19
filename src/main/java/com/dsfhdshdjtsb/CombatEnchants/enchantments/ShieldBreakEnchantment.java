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
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.Items;
import net.minecraft.item.ShieldItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ShieldBreakEnchantment extends Enchantment {
    public ShieldBreakEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.CROSSBOW, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
//        if(ModConfigs.SHIELDBREAK)
//            Registry.register(Registries.ENCHANTMENT, new Identifier("cenchants", "shield_break"), this);
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
