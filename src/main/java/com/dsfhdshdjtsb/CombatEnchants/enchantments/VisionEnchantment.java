package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import com.dsfhdshdjtsb.CombatEnchants.config.ModConfigs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;

public class VisionEnchantment extends Enchantment {
    public VisionEnchantment() {
        super(Enchantment.properties(ItemTags.HEAD_ARMOR_ENCHANTABLE,
                3, 2, Enchantment.leveledCost(5, 20),
                Enchantment.leveledCost(50, 20), 8, EquipmentSlot.HEAD));
        if(ModConfigs.VISION)
            Registry.register(Registries.ENCHANTMENT, new Identifier("cenchants", "vision"), this);
    }

    @Override
    public void onUserDamaged(LivingEntity user, Entity attacker, int level) {
        if(attacker instanceof LivingEntity)
            ((LivingEntity)(attacker)).addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, level * 30 + 20, 0));
    }


    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }
}
