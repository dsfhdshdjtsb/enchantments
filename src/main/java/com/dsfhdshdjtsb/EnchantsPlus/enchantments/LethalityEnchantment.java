package com.dsfhdshdjtsb.EnchantsPlus.enchantments;

import com.dsfhdshdjtsb.EnchantsPlus.EnchantsPlus;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.Hand;

public class LethalityEnchantment extends Enchantment {
    public LethalityEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinPower(int level) {
        return 5 + (level - 1) * 8;
    }

    @Override
    public int getMaxPower(int level) {
        return super.getMinPower(level) + 50;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(EnchantmentHelper.getLevel(EnchantsPlus.LETHALITY, user.getMainHandStack()) == 0)
            return;
        if (user instanceof PlayerEntity && target instanceof LivingEntity) {
            System.out.println(((LivingEntity) target).getArmor());
            System.out.println(user.getArmor());
            float bDamage = (((LivingEntity) target).getArmor() - user.getArmor()) / 2.0f;
            System.out.println(bDamage);
            target.damage(DamageSource.player((PlayerEntity) user),bDamage );
        }
        super.onTargetDamaged(user, target, level);
    }
}
