package com.dsfhdshdjtsb.CombatEnchants.mixin;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.EvokerFangsEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(TridentEntity.class)
public abstract class CenchantsTridentEntityMixin {

    @Shadow
    protected abstract ItemStack getDefaultItemStack();

    @Shadow private boolean dealtDamage;

    @Inject(at = @At("TAIL"), method = "onEntityHit")
    protected void onEntityHit(EntityHitResult entityHitResult, CallbackInfo ci)
    {
        TridentEntity trident = ((TridentEntity)(Object)(this));
        Entity entity = entityHitResult.getEntity();
        if (trident.getWorld() instanceof ServerWorld && EnchantmentHelper.getLevel(CombatEnchants.SNAP, getDefaultItemStack()) != 0 && trident.getOwner() != null) {
            if(!trident.getWorld().isRaining()) {
                entity.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(),entity.getX(),  entity.getY(),entity.getZ(), 0, 3, (LivingEntity) trident.getOwner()));
            }
            else if (!entity.getWorld().isThundering())
            {
                for(int i = -3; i < 3; i++)
                {
                    double xDif = entity.getX() - trident.getOwner().getX();
                    double zDif = entity.getZ() - trident.getOwner().getZ();
                    double angle = Math.atan(xDif/zDif);
                    entity.getWorld().spawnEntity(new EvokerFangsEntity(entity.getWorld(),entity.getX() + Math.sin(angle) * i,  entity.getY(),entity.getZ() + Math.cos(angle) * i, 0, (i + 3) * 2, (LivingEntity) trident.getOwner()));
                }
            }
            else
            {
                for(int i = -5; i < 5; i++)
                {
                    double xDif = entity.getX() - trident.getOwner().getX();
                    double zDif = entity.getZ() - trident.getOwner().getZ();
                    double angle = Math.atan(xDif/zDif);
                    EvokerFangsEntity fangsEntity = new EvokerFangsEntity(entity.getWorld(),entity.getX() + Math.sin(angle) * i,  entity.getY(),entity.getZ() + Math.cos(angle) * i, 0, (i + 5), (LivingEntity) trident.getOwner());
                    fangsEntity.setFireTicks(0);
                    entity.getWorld().spawnEntity(fangsEntity);
                }
            }
        }

        int inkingLevel = EnchantmentHelper.getLevel(CombatEnchants.INKING, getDefaultItemStack());
        if (trident.getWorld() instanceof ServerWorld && inkingLevel != 0 && trident.getOwner() != null && entity instanceof LivingEntity) {
            int chance = inkingLevel * 10;
            if(trident.getWorld().isRaining())
            {
                chance = 50;
            }
            if(trident.getWorld().isThundering()) {
                chance = 100;
            }
            Random rand = new Random();
            if(rand.nextInt(100) < chance)
            {
                ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, inkingLevel * 20 + 20, 0));
            }
        }
    }

    @Inject(at =  @At("TAIL"), method="tick")
    public void tick(CallbackInfo ci) {
        TridentEntity trident = ((TridentEntity)(Object)(this));
        if(!dealtDamage && EnchantmentHelper.getLevel(CombatEnchants.INKING, getDefaultItemStack()) != 0 && trident.getWorld() instanceof ServerWorld)
        {
            ((ServerWorld) trident.getWorld()).spawnParticles(ParticleTypes.SQUID_INK, trident.getX(), trident.getBodyY(0.5D), trident.getZ(), 0, 0.0D, 0.0D, 0.0D, 0.0D);
        }
    }

}
