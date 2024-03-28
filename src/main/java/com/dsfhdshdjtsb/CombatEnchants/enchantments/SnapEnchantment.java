package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import com.dsfhdshdjtsb.CombatEnchants.config.ModConfigs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;

public class SnapEnchantment extends Enchantment {
    public SnapEnchantment() {
        super(Enchantment.properties(ItemTags.TRIDENT_ENCHANTABLE,
                3, 1, Enchantment.constantCost(20),
                Enchantment.constantCost(50), 8, EquipmentSlot.MAINHAND));        if(ModConfigs.SNAP)
            Registry.register(Registries.ENCHANTMENT, new Identifier("cenchants", "snap"), this);
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {

    }


    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }
}
