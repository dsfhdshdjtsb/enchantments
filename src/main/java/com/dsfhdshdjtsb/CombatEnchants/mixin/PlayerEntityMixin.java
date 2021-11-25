package com.dsfhdshdjtsb.CombatEnchants.mixin;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import com.mojang.authlib.GameProfile;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin{

    @Inject(at = @At("HEAD"), method = "attack")
    private void attack(Entity target, CallbackInfo ci) {
        if (target.isAttackable() && !target.world.isClient) {
            if (!target.handleAttack((PlayerEntity)(Object) this) && target instanceof LivingEntity && !((PlayerEntity)(Object) this).handSwinging) {
                int combo = EnchantmentHelper.getLevel(CombatEnchants.COMBO, ((PlayerEntity)(Object) this).getMainHandStack());
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

    /**
     * @author me
     */
    @Overwrite
    public float getAttackCooldownProgressPerTick() {
        PlayerEntity entity = ((PlayerEntity)(Object) this);
        int curFervor = 0;
        StatusEffectInstance fervorInstance = entity.getStatusEffect(CombatEnchants.FERVOR_EFFECT);
        if(fervorInstance != null)
            curFervor = fervorInstance.getAmplifier();
        return (float)(1.0D / entity.getAttributeValue(EntityAttributes.GENERIC_ATTACK_SPEED) * (20.0D - (curFervor)));
    }

}
