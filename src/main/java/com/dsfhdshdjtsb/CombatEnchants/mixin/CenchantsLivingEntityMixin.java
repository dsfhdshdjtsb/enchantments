package com.dsfhdshdjtsb.CombatEnchants.mixin;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.*;

@Mixin(LivingEntity.class)
public abstract class CenchantsLivingEntityMixin extends Entity {

    public CenchantsLivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow public abstract Iterable<ItemStack> getArmorItems();

    @Shadow public abstract boolean addStatusEffect(StatusEffectInstance effect);

    @Shadow public abstract boolean removeStatusEffect(StatusEffect type);

    @Shadow public abstract boolean hasStatusEffect(StatusEffect effect);

    @Shadow @Nullable public abstract StatusEffectInstance getStatusEffect(StatusEffect effect);


    @Shadow public abstract ItemStack getMainHandStack();

    @Shadow public boolean handSwinging;

    @Shadow protected int itemUseTimeLeft;

    @Shadow public abstract ItemStack getEquippedStack(EquipmentSlot slot);

    @Shadow public abstract ItemStack getOffHandStack();

    @Shadow @Final private Map<StatusEffect, StatusEffectInstance> activeStatusEffects;

    @Shadow protected abstract void onStatusEffectRemoved(StatusEffectInstance effect);

    @Inject(at = @At("HEAD"), method = "tick")
    private void tick(CallbackInfo info) { //move to player entity
        int shieldingLevel = 0;
        int darkness = 0;
        for(ItemStack i : getArmorItems())
        {
            shieldingLevel += EnchantmentHelper.getLevel(CombatEnchants.SHIELDING, i);
            darkness += EnchantmentHelper.getLevel(CombatEnchants.DARKNESS, i);
        }
        if(shieldingLevel > 0 && !this.hasStatusEffect(CombatEnchants.SHIELDING_COOLDOWN_EFFECT))
        {
            StatusEffectInstance absorption = this.getStatusEffect(StatusEffects.ABSORPTION);
            if(absorption != null && absorption.getAmplifier() > shieldingLevel / 4)
                this.removeStatusEffect(StatusEffects.ABSORPTION);
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, shieldingLevel * 10 + 5, shieldingLevel / 4, false, false, true));
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

    @Inject(at = @At("HEAD"), method = "damage", cancellable = true)
    private void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if(source.isProjectile()) {
            int deflectLevel = EnchantmentHelper.getLevel(CombatEnchants.DEFLECT, this.getEquippedStack(EquipmentSlot.CHEST));
            if (deflectLevel != 0 && this.random.nextInt(100) < deflectLevel * 10) {
                if(this.world instanceof ServerWorld)
                {
                    ((ServerWorld) this.world).spawnParticles(ParticleTypes.POOF, this.getX(), this.getBodyY(0.5D), this.getZ(), 5, 0.3, 0.5, 0.3, 0.0D);
                }
                cir.cancel();
            }
        }
        int sorcLevel = 0;
        for(ItemStack i : getArmorItems())
        {
            if(EnchantmentHelper.getLevel(CombatEnchants.SHIELDING, i) != 0) {
                if(!this.hasStatusEffect(CombatEnchants.SHIELDING_COOLDOWN_EFFECT) && this.world instanceof ServerWorld)
                    ((ServerWorld) this.world).spawnParticles(CombatEnchants.SHIELD_PARTICLE, this.getX(), this.getBodyY(0.5D), this.getZ(), 3, 0.3D, 0.3D, 0.3D, 0.0D);
                this.addStatusEffect(new StatusEffectInstance(CombatEnchants.SHIELDING_COOLDOWN_EFFECT, 200));
                break;
            }
            int tempSorcLevel = EnchantmentHelper.getLevel(CombatEnchants.SORCERY, i);
            if(tempSorcLevel != 0)
            {
                sorcLevel += tempSorcLevel;
            }
        }
        if(sorcLevel != 0)
        {
            StatusEffect[] effects = {
                    StatusEffects.ABSORPTION,
                    StatusEffects.RESISTANCE,
                    StatusEffects.FIRE_RESISTANCE,
                    StatusEffects.HEALTH_BOOST,
                    StatusEffects.SPEED,
                    StatusEffects.REGENERATION,
            };

            Random rand = new Random();
            if(rand.nextInt(16) < sorcLevel) {
                StatusEffect randEffect = effects[rand.nextInt(6)];
                int duration = sorcLevel/4 * 3;
                if (this.getStatusEffect(randEffect) == null)
                    this.addStatusEffect(new StatusEffectInstance(randEffect, duration * 20, 0));
                else
                    this.addStatusEffect(new StatusEffectInstance(randEffect, duration * 20, 1));
            }
        }
        int tremor = EnchantmentHelper.getLevel(CombatEnchants.TREMOR, this.getEquippedStack(EquipmentSlot.FEET));
        if(tremor > 0 && source == DamageSource.FALL)
        {
            float damage = Math.min(10, amount);
            LivingEntity user = ((LivingEntity)((Object)this));
            List<LivingEntity> list = this.world.getNonSpectatingEntities(LivingEntity.class, this.getBoundingBox()
                    .expand(damage, 1D, damage));
            list.remove(user);

            for (LivingEntity e : list)
            {
                e.damage(DamageSource.mob(user), damage);
                e.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, (int)damage * 10, 1));
            }
            if (user.world instanceof ServerWorld)
            {
                System.out.println("test");
                for(double i = user.getX() - damage / 2; i <= user.getX() + damage / 2; i++)
                {
                    for(double j = user.getZ() - damage / 2; j <= user.getZ() + damage / 2; j++)
                    {
                        int x = MathHelper.floor(i);
                        int y = MathHelper.floor(this.getY() - 0.20000000298023224D);
                        int z = MathHelper.floor(j);
                        BlockPos blockPos = new BlockPos(x, y, z);
                        BlockState blockState = user.world.getBlockState(blockPos);
                        ((ServerWorld) user.world).spawnParticles( new BlockStateParticleEffect(ParticleTypes.BLOCK, blockState), x,
                                y + 1, z, 4, 1, 0.0D, 1, 0.0D);

                    }
                }
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

    @Inject(at = @At("HEAD"), method = "getItemUseTimeLeft", cancellable = true)
    public void getItemUseTimeLeft(CallbackInfoReturnable<Integer> cir) {
        if(this.hasStatusEffect(CombatEnchants.BARRAGE_EFFECT) && EnchantmentHelper.getLevel(CombatEnchants.BARRAGE, this.getMainHandStack()) != 0 || EnchantmentHelper.getLevel(CombatEnchants.BARRAGE, this.getOffHandStack()) != 0)
            cir.setReturnValue(0);
    }

    @ModifyConstant(method = "isBlocking", constant = @Constant(intValue = 5))
    public int modifyArmTime(int constant)
    {
        LivingEntity user = ((LivingEntity)((Object)this));
        int lightweightLevel = Math.max(0, Math.max(EnchantmentHelper.getLevel(CombatEnchants.LIGHTWEIGHT, user.getMainHandStack()),  EnchantmentHelper.getLevel(CombatEnchants.LIGHTWEIGHT, user.getOffHandStack())));
        return constant - lightweightLevel;
    }

    /**
     * @author Dsfhdshdjtsb
     */
    @Overwrite
    public boolean clearStatusEffects() {
        if (this.world.isClient) {
            return false;
        } else {
            StatusEffectInstance[] cooldowns = new StatusEffectInstance[]{this.getStatusEffect(CombatEnchants.LIFELINE_COOLDOWN_EFFECT),
                    this.getStatusEffect(CombatEnchants.LIFESTEAL_COOLDOWN_EFFECT),
                    this.getStatusEffect(CombatEnchants.SHIELDING_COOLDOWN_EFFECT)};
            Iterator<StatusEffectInstance> iterator = this.activeStatusEffects.values().iterator();

            boolean bl;
            for(bl = false; iterator.hasNext(); bl = true) {
                this.onStatusEffectRemoved((StatusEffectInstance)iterator.next());
                iterator.remove();
            }

            for(StatusEffectInstance i : cooldowns)
                if(i != null)
                    this.addStatusEffect(i);
            return bl;
        }
    }
}
