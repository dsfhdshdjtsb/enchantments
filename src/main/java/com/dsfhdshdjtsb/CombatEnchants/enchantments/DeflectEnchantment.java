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

public class DeflectEnchantment extends Enchantment {
    public DeflectEnchantment() {
        super(Enchantment.properties(ItemTags.CHEST_ARMOR_ENCHANTABLE,
                2, 2, Enchantment.leveledCost(5, 20),
                Enchantment.leveledCost(50, 20), 8, EquipmentSlot.CHEST));
        if(ModConfigs.DEFLECT)
            Registry.register(Registries.ENCHANTMENT, new Identifier("cenchants", "deflect"), this);
    }

    @Override
    public void onUserDamaged(LivingEntity user, Entity attacker, int level) {

    }


    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }
}
