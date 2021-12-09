package com.dsfhdshdjtsb.CombatEnchants.mixin;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.EvokerFangsEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TridentEntity.class)
public abstract class CenchantsTridentItemMixin{

    @Shadow private ItemStack tridentStack;

    @Inject(at = @At("TAIL"), method = "onEntityHit")
    protected void onEntityHit(EntityHitResult entityHitResult, CallbackInfo ci)
    {
        TridentEntity trident = ((TridentEntity)(Object)(this));
        Entity entity = entityHitResult.getEntity();
        if (trident.world instanceof ServerWorld && EnchantmentHelper.getLevel(CombatEnchants.SNAP, tridentStack) != 0 && trident.getOwner() != null) {
            if(!trident.world.isRaining()) {
                entity.world.spawnEntity(new EvokerFangsEntity(entity.world,entity.getX(),  entity.getY(),entity.getZ(), 0, 3, (LivingEntity) trident.getOwner()));
            }
            else if (!entity.world.isThundering())
            {
                for(int i = -3; i < 3; i++)
                {
                    double xDif = entity.getX() - trident.getOwner().getX();
                    double zDif = entity.getZ() - trident.getOwner().getZ();
                    double angle = Math.atan(xDif/zDif);
                    entity.world.spawnEntity(new EvokerFangsEntity(entity.world,entity.getX() + Math.sin(angle) * i,  entity.getY(),entity.getZ() + Math.cos(angle) * i, 0, (i + 3) * 2, (LivingEntity) trident.getOwner()));
                }
            }
            else
            {
                for(int i = -5; i < 5; i++)
                {
                    double xDif = entity.getX() - trident.getOwner().getX();
                    double zDif = entity.getZ() - trident.getOwner().getZ();
                    double angle = Math.atan(xDif/zDif);
                    EvokerFangsEntity fangsEntity = new EvokerFangsEntity(entity.world,entity.getX() + Math.sin(angle) * i,  entity.getY(),entity.getZ() + Math.cos(angle) * i, 0, (i + 5), (LivingEntity) trident.getOwner());
                    fangsEntity.setFireTicks(0);
                    entity.world.spawnEntity(fangsEntity);
                }
            }
        }
    }
}
