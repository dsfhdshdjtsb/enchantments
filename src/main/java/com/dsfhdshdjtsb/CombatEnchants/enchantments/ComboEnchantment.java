package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import com.dsfhdshdjtsb.CombatEnchants.config.ModConfigs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.world.World;


public class ComboEnchantment extends Enchantment {
    public ComboEnchantment() {
        super(Enchantment.properties(ItemTags.SWORD_ENCHANTABLE,
                5, 5, Enchantment.leveledCost(1, 11),
                Enchantment.leveledCost(21, 11), 1, EquipmentSlot.MAINHAND));
        if(ModConfigs.COMBO)
            Registry.register(Registries.ENCHANTMENT, new Identifier("cenchants", "combo"), this);
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (EnchantmentHelper.getLevel(CombatEnchants.COMBO, user.getMainHandStack()) == 0||target.distanceTo(user) >= 6)
            return;
        if(target instanceof LivingEntity && user.getWorld() instanceof ServerWorld)
        {
            StatusEffectInstance mark = ((LivingEntity) target).getStatusEffect(CombatEnchants.MARK_EFFECT);
            if(mark != null && mark.getAmplifier() > 8 - level)
            {
                user.addStatusEffect(new StatusEffectInstance(CombatEnchants.LIGHTNING_IMMUNE, 30, 1, true, false));
                ((LivingEntity) target).removeStatusEffect(CombatEnchants.LIGHTNING_IMMUNE);
                World world = target.getWorld();
                LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(world);
                assert lightning != null;
                lightning.setPos(target.getX(), target.getY(), target.getZ());
                world.spawnEntity(lightning);
                ((LivingEntity) target).removeStatusEffect(CombatEnchants.MARK_EFFECT);
                ((ServerWorld) user.getWorld()).spawnParticles(ParticleTypes.END_ROD, target.getX(), target.getBodyY(0.5D), target.getZ(), 20, 0.3, 0.5, 0.3, 0.5D);

            }
        }

    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }
}

