package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import com.dsfhdshdjtsb.CombatEnchants.config.ModConfigs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;

import java.util.Random;

import static com.dsfhdshdjtsb.CombatEnchants.CombatEnchants.ALL_ARMOR;

public class SorceryEnchantment extends Enchantment {
    public SorceryEnchantment() {
        super(Enchantment.properties(ItemTags.ARMOR_ENCHANTABLE,
                5, 4, Enchantment.leveledCost(1, 11),
                Enchantment.leveledCost(12, 11), 8, ALL_ARMOR));
        if(ModConfigs.SORCERY)
            Registry.register(Registries.ENCHANTMENT, new Identifier("cenchants", "sorcery"), this);
    }


    @Override
    public void onUserDamaged(LivingEntity user, Entity attacker, int level) {

    }


    @Override
    protected boolean canAccept(Enchantment other) {
        if(other.equals(CombatEnchants.SHIELDING))
            return false;
        return super.canAccept(other);
    }
}
