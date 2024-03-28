package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
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

public class FlameWalkerEnchantment extends Enchantment {
    public FlameWalkerEnchantment() {
        super(Enchantment.properties(ItemTags.FOOT_ARMOR_ENCHANTABLE,
                2, 2, Enchantment.leveledCost(5, 20),
                Enchantment.leveledCost(50, 20), 8, EquipmentSlot.FEET));
        if(ModConfigs.FLAMEWALKER)
            Registry.register(Registries.ENCHANTMENT, new Identifier("cenchants", "flame_walker"), this);
    }

    @Override
    public void onUserDamaged(LivingEntity user, Entity attacker, int level) {
        user.addStatusEffect(new StatusEffectInstance(CombatEnchants.FIRE_WALK_EFFECT, 50 * level, 0));
        user.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 50 * level, 0));
    }


    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }
}