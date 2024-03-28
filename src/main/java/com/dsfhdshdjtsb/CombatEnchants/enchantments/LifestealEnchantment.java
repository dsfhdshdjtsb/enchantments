package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import com.dsfhdshdjtsb.CombatEnchants.config.ModConfigs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.world.GameRules;

import java.util.List;

public class LifestealEnchantment extends Enchantment {
    public LifestealEnchantment() {
        super(Enchantment.properties(ItemTags.SWORD_ENCHANTABLE,
                10, 5, Enchantment.leveledCost(1, 11),
                Enchantment.leveledCost(21, 11), 1, EquipmentSlot.MAINHAND));
        if(ModConfigs.LIFESTEAL)
            Registry.register(Registries.ENCHANTMENT, new Identifier("cenchants", "lifesteal"), this);
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (EnchantmentHelper.getLevel(CombatEnchants.LIFESTEAL, user.getMainHandStack()) == 0 || target.distanceTo(user) >= 6 || !(target instanceof LivingEntity))
            return;
        if (user.getStatusEffect(CombatEnchants.LIFESTEAL_COOLDOWN_EFFECT) == null) {
            List<LivingEntity> list = target.getWorld().getNonSpectatingEntities(LivingEntity.class, target.getBoundingBox()
                    .expand(1 + level, 0.25D, 1 + level));
            int counter = 0;

            for (LivingEntity e : list) {
                if (!e.equals(user)) {
                    counter++;
                    e.damage(target.getWorld().getDamageSources().magic(), 1.0f);

                    if (target.getWorld() instanceof ServerWorld) {
                        double xdif = e.getX() - user.getX();
                        double ydif = e.getBodyY(0.5D) - user.getBodyY(0.5D);
                        double zdif = e.getZ() - user.getZ();

                        int particleNumConstant = 20; //number of particles
                        double x = 0;
                        double y = 0;
                        double z = 0;
                        while(Math.abs(x) < Math.abs(xdif))
                        {
                            ((ServerWorld) target.getWorld()).spawnParticles(ParticleTypes.COMPOSTER, user.getX() + x,
                                    user.getBodyY(0.5D) + y, user.getZ() + z, 0, 1, 0.0D, 1, 0.0D);
                            x = x + xdif/particleNumConstant;
                            y = y + ydif/particleNumConstant;
                            z = z + zdif/particleNumConstant;
                        }
                    }
                }
            }
            user.heal(counter + (level));
            user.addStatusEffect(new StatusEffectInstance(CombatEnchants.LIFESTEAL_COOLDOWN_EFFECT, 300 - (level * 20)));
            if(user.getWorld() instanceof ServerWorld)
                ((ServerWorld) user.getWorld()).spawnParticles(ParticleTypes.HEART, user.getX(), user.getBodyY(0.5D), user.getZ(), 3, 0.4, 0.5, 0.4, 0.0D);
        }
    }


    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }
}
