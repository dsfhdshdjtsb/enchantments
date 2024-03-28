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

public class DarknessCurse extends Enchantment {
    public DarknessCurse() {
        super(Enchantment.properties(ItemTags.HEAD_ARMOR_ENCHANTABLE,
                1, 1, Enchantment.constantCost(20),
                Enchantment.constantCost(50), 8, EquipmentSlot.HEAD));
        if(ModConfigs.DARKNESS)
            Registry.register(Registries.ENCHANTMENT, new Identifier("cenchants", "darkness"), this);
    }


    @Override
    public void onUserDamaged(LivingEntity user, Entity attacker, int level) {

    }

    @Override
    public boolean isCursed() {
        return true;
    }

    @Override
    public boolean isTreasure() {
        return true;
    }


    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }

}
