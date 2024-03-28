package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import com.dsfhdshdjtsb.CombatEnchants.config.ModConfigs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.CrossbowItem;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;

public class KnockupEnchantment extends Enchantment {
    public KnockupEnchantment() {
        super(Enchantment.properties(ItemTags.BOW_ENCHANTABLE,
                3, 2, Enchantment.leveledCost(5, 20),
                Enchantment.leveledCost(50, 20), 8, EquipmentSlot.MAINHAND));
        if(ModConfigs.KNOCKUP)
            Registry.register(Registries.ENCHANTMENT, new Identifier("cenchants", "knockup"), this);
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(target instanceof LivingEntity) {
            DamageSource damageSource = ((LivingEntity) target).getRecentDamageSource();
            if(damageSource != null && !damageSource.getType().msgId().equals("arrow") || (user.getMainHandStack().getItem() instanceof CrossbowItem))
                return;
        }
        if(target instanceof LivingEntity)
        {
            target.addVelocity( 0, (level / 6.0f) * (1.0D - ((LivingEntity)(target)).getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE) * 0.5), 0);
        }
    }


    @Override
    protected boolean canAccept(Enchantment other) {
        if(other.equals(Enchantments.PUNCH) || other.equals(CombatEnchants.GRAB))
            return false;
        return super.canAccept(other);
    }
}
