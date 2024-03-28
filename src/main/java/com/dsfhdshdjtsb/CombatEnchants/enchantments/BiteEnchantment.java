package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import com.dsfhdshdjtsb.CombatEnchants.config.ModConfigProvider;
import com.dsfhdshdjtsb.CombatEnchants.config.ModConfigs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.world.World;

public class BiteEnchantment extends Enchantment {
    public BiteEnchantment() {
        super(Enchantment.properties(ItemTags.CROSSBOW_ENCHANTABLE,
                5, 2, Enchantment.leveledCost(5, 20),
                Enchantment.leveledCost(50, 20), 8, EquipmentSlot.MAINHAND));
        if(ModConfigs.BITE)
            Registry.register(Registries.ENCHANTMENT, new Identifier("cenchants", "bite"), this);
    }


    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(target instanceof LivingEntity) {
            DamageSource damageSource = ((LivingEntity) target).getRecentDamageSource();
            if(damageSource != null && !damageSource.getType().msgId().equals("arrow") || (user.getMainHandStack().getItem() instanceof BowItem))
                return;
        }
        if(target.getWorld() instanceof ServerWorld && target instanceof LivingEntity && user instanceof PlayerEntity) {
            World world = target.getWorld();
            WolfEntity wolf = EntityType.WOLF.create(world);
            double posX = Math.random() * 2 - 1;
            double posY = Math.random() * 2 - 1;
            assert wolf != null;
            wolf.setPos(target.getX() + posX, target.getY() + 1, target.getZ() + posY);
            wolf.setTarget((LivingEntity) target);
            wolf.setHealth(4 * level);
            wolf.addStatusEffect(new StatusEffectInstance(CombatEnchants.DELAYED_DEATH_EFFECT, 60 * level + 10, 100));
            world.spawnEntity(wolf);
        }
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }
}
