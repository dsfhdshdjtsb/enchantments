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

public class AntihealEnchantment extends Enchantment {
    public AntihealEnchantment() {
        super(Enchantment.properties(ItemTags.CROSSBOW_ENCHANTABLE,
                5, 1, Enchantment.constantCost(20),
                Enchantment.constantCost(50), 8, EquipmentSlot.MAINHAND));
        if(ModConfigs.ANTIHEAL)
            Registry.register(Registries.ENCHANTMENT, new Identifier("cenchants", "antiheal"), this);
    }


    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(target instanceof LivingEntity) {
            DamageSource damageSource = ((LivingEntity) target).getRecentDamageSource();
            if(damageSource != null && !damageSource.getType().msgId().equals("arrow") || (user.getMainHandStack().getItem() instanceof BowItem))
                return;
        }
        if(target instanceof LivingEntity)
            ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(CombatEnchants.ANTIHEAL_EFFECT, 160, level));
    }



    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }
}
