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
import net.minecraft.item.CrossbowItem;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;

import java.util.Objects;

public class HunterEnchantment extends Enchantment {
    public HunterEnchantment() {
        super(Enchantment.properties(ItemTags.BOW_ENCHANTABLE,
                5, 2, Enchantment.leveledCost(5, 20),
                Enchantment.leveledCost(50, 20), 8, EquipmentSlot.MAINHAND));
        if(ModConfigs.HUNTER)
            Registry.register(Registries.ENCHANTMENT, new Identifier("cenchants", "hunter"), this);
    }
    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(target instanceof LivingEntity) {
            DamageSource damageSource = ((LivingEntity) target).getRecentDamageSource();

            if(damageSource != null && !damageSource.getType().msgId().equals("arrow") || (user.getMainHandStack().getItem() instanceof CrossbowItem))
                return;
        }
        if(target instanceof LivingEntity) {
            if(((LivingEntity) target).getStatusEffect(CombatEnchants.MARK_EFFECT) == null)
            {
                ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(CombatEnchants.MARK_EFFECT, 1, 5));
                ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 100, 0));
            }
            else if(Objects.requireNonNull(((LivingEntity) target).getStatusEffect(CombatEnchants.MARK_EFFECT)).getAmplifier() == 0)
            {
                ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(CombatEnchants.MARK_EFFECT, 60, 0));
                ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 60, 0));
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 150 * level, 1));
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 150 * level, 1));
            }
        }
    }


    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }
}
