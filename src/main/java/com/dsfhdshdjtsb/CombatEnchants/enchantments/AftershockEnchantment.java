package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import com.dsfhdshdjtsb.CombatEnchants.config.ModConfigs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;

import static com.dsfhdshdjtsb.CombatEnchants.CombatEnchants.ALL_ARMOR;

public class AftershockEnchantment extends Enchantment {
    public AftershockEnchantment() {
//        super(Rarity.VERY_RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        super(Enchantment.properties(ItemTags.SWORD_ENCHANTABLE,
                3, 5, Enchantment.leveledCost(1, 11),
                Enchantment.leveledCost(21, 11), 1, EquipmentSlot.MAINHAND));
        if(ModConfigs.AFTERSHOCK)
            Registry.register(Registries.ENCHANTMENT, new Identifier("cenchants", "aftershock"), this);
    }


    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }
}
