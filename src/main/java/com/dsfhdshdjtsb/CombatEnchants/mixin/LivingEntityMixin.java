package com.dsfhdshdjtsb.CombatEnchants.mixin;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;
import java.util.Random;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow public abstract Iterable<ItemStack> getArmorItems();

    @Shadow public abstract void setStatusEffect(StatusEffectInstance effect, @Nullable Entity source);

    @Shadow public abstract boolean addStatusEffect(StatusEffectInstance effect);

    @Shadow public abstract boolean removeStatusEffect(StatusEffect type);

    @Shadow public abstract boolean hasStatusEffect(StatusEffect effect);

    @Shadow @Nullable public abstract StatusEffectInstance getStatusEffect(StatusEffect effect);


    @Shadow public abstract ItemStack getMainHandStack();

    @Shadow public boolean handSwinging;

    @Shadow public float sidewaysSpeed;

    @Inject(at = @At("HEAD"), method = "tick")
    private void tick(CallbackInfo info) {
        int shieldingLevel = 0;
        int darkness = 0;
        for(ItemStack i : getArmorItems())
        {
            shieldingLevel += EnchantmentHelper.getLevel(CombatEnchants.SHEILDING, i);
            darkness += EnchantmentHelper.getLevel(CombatEnchants.DARKNESS, i);
        }

        System.out.println("shielding: " +shieldingLevel + " darkness: " + darkness);
        if(shieldingLevel > 0 && !this.hasStatusEffect(CombatEnchants.SHIELDING_COOLDOWN_EFFECT))
        {
            StatusEffectInstance absorption = this.getStatusEffect(StatusEffects.ABSORPTION);
            if(absorption != null && absorption.getAmplifier() > shieldingLevel / 4)
                this.removeStatusEffect(StatusEffects.ABSORPTION);
            this.setStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, shieldingLevel * 10 + 5, shieldingLevel / 4, false, false, true), null);
        }
        if(darkness != 0)
        {
            if(this.world.getLightLevel(this.getBlockPos()) >= 10 ) {
                this.setFireTicks(20);
            }
            else {
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 45, 0));
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 45, 0));

            }
        }
    }

    @Inject(at = @At("HEAD"), method = "damage")
    private void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        for(ItemStack i : getArmorItems())
        {
            if(EnchantmentHelper.getLevel(CombatEnchants.SHEILDING, i) != 0) {
                this.setStatusEffect(new StatusEffectInstance(CombatEnchants.SHIELDING_COOLDOWN_EFFECT, 200), null);
                break;
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "onAttacking")
    public void onAttacking(Entity target, CallbackInfo ci) {
        if(!(this.world.getLightLevel(this.getBlockPos()) >= 12)) {
            for (ItemStack i : this.getArmorItems()) {
                if (EnchantmentHelper.getLevel(CombatEnchants.DARKNESS, i) != 0) { //FIX LIGHT LEVEL
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0));
                    break;
                }
            }
        }

        int fervorLevel = EnchantmentHelper.getLevel(CombatEnchants.FERVOR, this.getMainHandStack());
        if(fervorLevel != 0)
        {
            int curFervor = 0;
            StatusEffectInstance fervorInstance = this.getStatusEffect(CombatEnchants.FERVOR_EFFECT);
            if(fervorInstance != null) {
                curFervor = fervorInstance.getAmplifier();
                if (curFervor < fervorLevel * 2 - 1 && !this.handSwinging)
                    curFervor++;
            }
            this.addStatusEffect(new StatusEffectInstance(CombatEnchants.FERVOR_EFFECT,  20 + fervorLevel * 12, curFervor));
        }
    }

    @Inject(at = @At("HEAD"), method = "wakeUp", cancellable = true)
    private void init(CallbackInfo ci) {
        if(this.getStatusEffect(CombatEnchants.SLEEPY_EFFECT) != null) {
            ci.cancel();
        }
    }

    @Inject(at = @At("HEAD"), method = "heal", cancellable = true)
    private void heal(CallbackInfo ci) {
        if(this.getStatusEffect(CombatEnchants.ANTIHEAL_EFFECT) != null) {
            ci.cancel();
        }
    }
}
