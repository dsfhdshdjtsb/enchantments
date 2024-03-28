package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import com.dsfhdshdjtsb.CombatEnchants.config.ModConfigs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;

import java.util.List;
import java.util.Objects;

public class TremorEnchantment extends Enchantment {
    public TremorEnchantment() {
        super(Enchantment.properties(ItemTags.FOOT_ARMOR_ENCHANTABLE,
                2, 1, Enchantment.constantCost(20),
                Enchantment.constantCost(50), 8, EquipmentSlot.FEET));        if(ModConfigs.TREMOR)
            Registry.register(Registries.ENCHANTMENT, new Identifier("cenchants", "tremor"), this);
    }

    @Override
    public void onUserDamaged(LivingEntity user, Entity attacker, int level) {

    }


    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }
}
