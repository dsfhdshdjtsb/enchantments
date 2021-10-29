package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class AggressionEnchantment extends Enchantment {
    public AggressionEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentTarget.CROSSBOW, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinPower(int level) {
        return 0;
    }

    @Override
    public int getMaxPower(int level) {
        return 15;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(target.distanceTo(user) < 4)
            return;
        if(target instanceof LivingEntity && user instanceof PlayerEntity) {
            World world = target.world;
            WolfEntity wolf = EntityType.WOLF.create(world);
            double posX = Math.random() * 2 - 1;
            double posY = Math.random() * 2 - 1;
            assert wolf != null;
            wolf.setPos(target.getX() + posX, target.getY() + 1, target.getZ() + posY);
            wolf.setTarget((LivingEntity) target);
            wolf.addStatusEffect(new StatusEffectInstance(CombatEnchants.DELAYED_DEATH_EFFECT, 110, 0));
            world.spawnEntity(wolf);
        }
        super.onTargetDamaged(user, target, level);
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }
}
