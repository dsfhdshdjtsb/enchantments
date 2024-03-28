package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import com.dsfhdshdjtsb.CombatEnchants.config.ModConfigs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;

import java.util.List;

public class DuelingEnchantment  extends Enchantment {
    public DuelingEnchantment() {
        super(Enchantment.properties(ItemTags.SWORD_ENCHANTABLE,
                3, 1, Enchantment.constantCost(20),
                Enchantment.constantCost(50), 8, EquipmentSlot.MAINHAND));
        if(ModConfigs.DUELING)
            Registry.register(Registries.ENCHANTMENT, new Identifier("cenchants", "dueling"), this);
    }
    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        //check that enchant is not in offhand, and that player isnt using a bow and then switching to sword
        if(EnchantmentHelper.getLevel(CombatEnchants.DUELING, user.getMainHandStack()) == 0|| target.distanceTo(user) >= 6)
            return;

        List<LivingEntity> list = target.getWorld().getNonSpectatingEntities(LivingEntity.class, target.getBoundingBox().expand(5.0D, 0.25D, 5.0D));
        boolean bl = false;
        for (LivingEntity e : list) {
            if (!e.equals(user) && !e.equals(target)) {
                bl = true;
                e.takeKnockback(1, target.getX() - e.getX(), target.getZ() - e.getZ());
            }
        }

        if (bl && target.getWorld() instanceof ServerWorld) {
            for (double x = -6; x <= 6; x = x + 1) {
                double y = Math.sqrt(36 - x * x);
                ((ServerWorld) target.getWorld()).spawnParticles(ParticleTypes.CLOUD, target.getX() + x, target.getBodyY(0.5D), target.getZ() + y, 0, 1, 0.0D, 1, 0.0D);
                ((ServerWorld) target.getWorld()).spawnParticles(ParticleTypes.CLOUD, target.getX() + x, target.getBodyY(0.5D), target.getZ() - y, 0, 1, 0.0D, 1, 0.0D);
            }
        }
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }
}
