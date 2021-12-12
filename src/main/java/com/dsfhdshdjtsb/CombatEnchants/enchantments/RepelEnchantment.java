package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import com.github.crimsondawn45.fabricshieldlib.lib.event.ShieldBlockCallback;
import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricShieldItem;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.MathHelper;

public class RepelEnchantment extends Enchantment {
    public RepelEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.BREAKABLE, new EquipmentSlot[] {EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});

    }

    @Override
    public int getMinPower(int level) {
        return 10 + 20 * (level - 1);
    }

    @Override
    public int getMaxPower(int level) {
        return super.getMinPower(level) + 50;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof ShieldItem || stack.getItem() instanceof FabricShieldItem;
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other);
    }

    public static void onInitialize(){
        ShieldBlockCallback.EVENT.register((defender, source, amount, hand, shield) -> {

            int repelLevel = Math.max(0, EnchantmentHelper.getLevel(CombatEnchants.REPEL, shield));
            if(repelLevel != 0) {
                Entity attacker = source.getAttacker();

                assert attacker != null;
                if(defender instanceof PlayerEntity && attacker instanceof LivingEntity) {  //Defender should always be a player, but check anyways
                    double j = repelLevel * (1.0D - defender.getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE));
                    ((LivingEntity) attacker).takeKnockback((j), MathHelper.sin(defender.getYaw() * 0.017453292F), (-MathHelper.cos(defender.getYaw() * 0.017453292F)));
                }
            }

            return ActionResult.PASS;
        });
    }
}
