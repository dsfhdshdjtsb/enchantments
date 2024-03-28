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

public class InkingEnchantment extends Enchantment {
    public InkingEnchantment() {
        super(Enchantment.properties(ItemTags.TRIDENT_ENCHANTABLE,
                3, 2, Enchantment.leveledCost(5, 20),
                Enchantment.leveledCost(50, 20), 8, EquipmentSlot.MAINHAND));        if(ModConfigs.INKING)
            Registry.register(Registries.ENCHANTMENT, new Identifier("cenchants", "inking"), this);
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
    }


    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }
}

