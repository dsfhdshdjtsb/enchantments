package com.dsfhdshdjtsb.CombatEnchants.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ShieldItem;

public class CenchantsShieldTarget extends CenchantsEnchantmentTargetMixin {

    @Override
    public boolean isAcceptableItem(Item item) {
        return item instanceof ShieldItem;
    }
}
