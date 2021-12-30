package com.dsfhdshdjtsb.CombatEnchants.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EnchantmentHelper.class)
public abstract class CenchantsEnchantmentHelperMixin {
//    @Redirect(at = @At(value="INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentTarget;isAcceptableItem(Lnet/minecraft/item/Item;)Z"), method = "getPossibleEntries"  )
//    private static boolean isAcceptableItem(EnchantmentTarget instance, Item item)
//    {
//
//        return false;
//    }
}
