package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import com.dsfhdshdjtsb.CombatEnchants.config.ModConfigs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.BowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;

public class HookEnchantment extends Enchantment {
    public HookEnchantment() {
        super(Enchantment.properties(ItemTags.CROSSBOW_ENCHANTABLE,
                3, 2, Enchantment.leveledCost(5, 20),
                Enchantment.leveledCost(50, 20), 8, EquipmentSlot.MAINHAND));
        if(ModConfigs.HOOK)
            Registry.register(Registries.ENCHANTMENT, new Identifier("cenchants", "hook"), this);
    }
    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(target instanceof LivingEntity) {
            DamageSource damageSource = ((LivingEntity) target).getRecentDamageSource();
            if(damageSource != null && !damageSource.getType().msgId().equals("arrow") || (user.getMainHandStack().getItem() instanceof BowItem))
                return;
        }
        if(target instanceof LivingEntity) {
            target.setVelocity(0, 0, 0);
            ((LivingEntity)target).takeKnockback(level * 0.5, -MathHelper.sin(user.getYaw() * 0.017453292F),(MathHelper.cos(user.getYaw() * 0.017453292F)));
        }
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }
}
