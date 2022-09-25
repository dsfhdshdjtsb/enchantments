package com.dsfhdshdjtsb.CombatEnchants.mixin;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class CenchantsPlayerEntityMixin {

    @Inject(at = @At("HEAD"), method = "attack")
    private void attack(Entity target, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity)(Object) this;
        if (target.isAttackable() && !target.world.isClient) {
            if (!target.handleAttack(player) && target instanceof LivingEntity && !((PlayerEntity)(Object) this).handSwinging) {
                int chargeLevel = EnchantmentHelper.getLevel(CombatEnchants.CHARGE, (player.getMainHandStack()));
                if (player.isSprinting() && chargeLevel !=0){
                    float damage = (float)player.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE) + EnchantmentHelper.getAttackDamage(player.getMainHandStack(), player.getGroup());
                    target.damage(DamageSource.player((PlayerEntity) player), damage + (chargeLevel*1.25f+1.5f));
                    if (target.world instanceof ServerWorld) {
                        ((ServerWorld) player.world).spawnParticles(ParticleTypes.END_ROD, target.getX(), target.getBodyY(0.5D), target.getZ(), 20, 0.3, 0.5, 0.3, 0.5D);
                    }
                    player.setSprinting(false);
                }
                int combo = EnchantmentHelper.getLevel(CombatEnchants.COMBO, (player).getMainHandStack());
                if(combo != 0)
                {
                    int mark = 0;
                    StatusEffectInstance markInstance = ((LivingEntity) target).getStatusEffect(CombatEnchants.MARK_EFFECT);
                    if( markInstance!= null)
                        mark = markInstance.getAmplifier() + 1;
                    ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(CombatEnchants.MARK_EFFECT, 20 + combo * 4, mark));

                }
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "getAttackCooldownProgressPerTick", cancellable = true)
    public void getAttackCooldownProgressPerTick(CallbackInfoReturnable<Float> cir) {
        PlayerEntity entity = ((PlayerEntity)(Object) this);
        StatusEffectInstance fervorInstance = entity.getStatusEffect(CombatEnchants.FERVOR_EFFECT);
        if(fervorInstance != null) {
            int curFervor = fervorInstance.getAmplifier();
            cir.setReturnValue((float)(1.0D / entity.getAttributeValue(EntityAttributes.GENERIC_ATTACK_SPEED) * (20.0D - (curFervor))));
        }
    }

    @Inject(at = @At("HEAD"), method = "tick")
    public void tick(CallbackInfo ci)
    {
        LivingEntity user = ((LivingEntity)((Object)this));
        if(user.isBlocking()) {
            int steadfastLevel = Math.max(0, Math.max(EnchantmentHelper.getLevel(CombatEnchants.STEADFAST, user.getMainHandStack()), EnchantmentHelper.getLevel(CombatEnchants.STEADFAST, user.getOffHandStack())));
            if (steadfastLevel != 0) {
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 5, steadfastLevel - 1, true, false));
                if (user.world instanceof ServerWorld) {
                    ((ServerWorld) user.world).spawnParticles(ParticleTypes.ELECTRIC_SPARK, user.getX(), user.getBodyY(0.5D), user.getZ(), 1, 0.4, 0.1, 0.4, 0.0D);
                }
            }

        }
    }

    @ModifyArg(method = "disableShield", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/ItemCooldownManager;set(Lnet/minecraft/item/Item;I)V"), index = 1)
    public int modifyDisableTime(int duration)
    {
        LivingEntity user = ((LivingEntity)((Object)this));
        int lightweightLevel = Math.max(0, Math.max(EnchantmentHelper.getLevel(CombatEnchants.LIGHTWEIGHT, user.getMainHandStack()),  EnchantmentHelper.getLevel(CombatEnchants.LIGHTWEIGHT, user.getOffHandStack())));
        return duration - lightweightLevel * 10;
    }
}
