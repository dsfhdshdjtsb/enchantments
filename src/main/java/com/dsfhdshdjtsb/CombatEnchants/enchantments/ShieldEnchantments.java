package com.dsfhdshdjtsb.CombatEnchants.enchantments;

import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricShieldEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ShieldEnchantments {

    public static final FabricShieldEnchantment STEADFAST = new FabricShieldEnchantment(Enchantment.Rarity.COMMON);

    public static void onInitialize()
    {
        Registry.register(Registry.ENCHANTMENT, new Identifier("Cenchants", "steadfast"), STEADFAST);

    }
}
