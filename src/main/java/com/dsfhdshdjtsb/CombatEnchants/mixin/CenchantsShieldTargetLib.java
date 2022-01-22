package com.dsfhdshdjtsb.CombatEnchants.mixin;

import com.chocohead.mm.api.ClassTinkerers;
import net.minecraft.enchantment.EnchantmentTarget;

public class CenchantsShieldTargetLib {
    public static final EnchantmentTarget SHIELD_TARGET = ClassTinkerers.getEnum(EnchantmentTarget.class, "SHIELD");
}
