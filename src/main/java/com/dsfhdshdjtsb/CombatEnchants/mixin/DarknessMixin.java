package com.dsfhdshdjtsb.CombatEnchants.mixin;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
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

import java.util.Objects;
import java.util.Random;

@Mixin(LivingEntity.class)
public abstract class DarknessMixin extends Entity {

    public DarknessMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow @Final private DefaultedList<ItemStack> equippedArmor;

    @Shadow public abstract void setStatusEffect(StatusEffectInstance effect, @Nullable Entity source);

    @Shadow public abstract boolean addStatusEffect(StatusEffectInstance effect);

    @Shadow public abstract boolean removeStatusEffect(StatusEffect type);

    @Inject(at = @At("HEAD"), method = "tick")
    private void tick(CallbackInfo info) {
        ItemStack helmet = equippedArmor.get(3);

        if(EnchantmentHelper.getLevel(CombatEnchants.DARKNESS, helmet) != 0)
        {

            if(this.world.getLightLevel(this.getBlockPos()) >= 12 ) {
                this.setFireTicks(20);
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 41, 0));
                this.removeStatusEffect(StatusEffects.JUMP_BOOST);
                this.removeStatusEffect(StatusEffects.SPEED);
                this.removeStatusEffect(StatusEffects.FIRE_RESISTANCE);
            }
            else {
                this.removeStatusEffect(StatusEffects.WEAKNESS);
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 41, 0));
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 41, 0));

            }
        }
    }

    @Inject(at = @At("HEAD"), method = "onAttacking")
    public void onAttacking(Entity target, CallbackInfo ci) {
        if(!(this.world.getLightLevel(this.getBlockPos()) >= 12)) {
            for (ItemStack i : this.getArmorItems()) {
                if (EnchantmentHelper.getLevel(CombatEnchants.DARKNESS, i) != 0) {
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1));
                    break;
                }
            }
        }
    }
}
