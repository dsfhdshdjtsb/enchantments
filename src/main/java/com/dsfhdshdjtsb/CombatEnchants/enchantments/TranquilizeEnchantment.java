package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import com.dsfhdshdjtsb.CombatEnchants.config.ModConfigs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.BowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;

public class TranquilizeEnchantment extends Enchantment {
    public TranquilizeEnchantment() {
        super(Enchantment.properties(ItemTags.CROSSBOW_ENCHANTABLE,
                2, 2, Enchantment.leveledCost(5, 20),
                Enchantment.leveledCost(50, 20), 8, EquipmentSlot.MAINHAND));        if(ModConfigs.TRANQUILIZE)
            Registry.register(Registries.ENCHANTMENT, new Identifier("cenchants", "tranquilize"), this);
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(target instanceof LivingEntity) {
            DamageSource damageSource = ((LivingEntity) target).getRecentDamageSource();
            if(damageSource != null && !damageSource.getType().msgId().equals("arrow") || (user.getMainHandStack().getItem() instanceof BowItem))
                return;
            ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(CombatEnchants.SLEEPY_EFFECT, 200, level + 1));
            ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(CombatEnchants.SLEEPY_PARTICLE_EFFECT, 200, 0, false, false));

        }

    }


    @Override
    protected boolean canAccept(Enchantment other) {
        if(other.equals(CombatEnchants.FROST))
            return false;
        return super.canAccept(other);
    }
}
