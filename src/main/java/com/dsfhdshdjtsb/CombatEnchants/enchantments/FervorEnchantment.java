package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import com.dsfhdshdjtsb.CombatEnchants.config.ModConfigs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.*;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;

public class FervorEnchantment extends Enchantment {
    public FervorEnchantment() {
        super(Enchantment.properties(ItemTags.SWORD_ENCHANTABLE,
                5, 5, Enchantment.leveledCost(1, 11),
                Enchantment.leveledCost(21, 11), 1, EquipmentSlot.MAINHAND));
        if(ModConfigs.FERVOR)
            Registry.register(Registries.ENCHANTMENT, new Identifier("cenchants", "fervor"), this);
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
    }



    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }
}
