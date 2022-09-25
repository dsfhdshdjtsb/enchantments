package com.dsfhdshdjtsb.CombatEnchants.mixin;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.BowItem;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(PersistentProjectileEntity.class)

public abstract class CenchantsPersistentProjectileEntityMixin {
    @Shadow protected boolean inGround;

    @Inject(at = @At("TAIL"), method = "tick")
    public void tick(CallbackInfo ci) {
        PersistentProjectileEntity thisEntity = (PersistentProjectileEntity) (Object) this;
        if (thisEntity.getOwner() instanceof LivingEntity) {
            LivingEntity owner = (LivingEntity) thisEntity.getOwner();
            if (owner.getMainHandStack().getItem() instanceof BowItem){
                return;
            }
            if (this.inGround && thisEntity instanceof ArrowEntity) {
                thisEntity.world.createExplosion(thisEntity, thisEntity.getX(), thisEntity.getY(), thisEntity.getZ(),1.5f, Explosion.DestructionType.BREAK);
                thisEntity.discard();
            }


        }




    }
}



