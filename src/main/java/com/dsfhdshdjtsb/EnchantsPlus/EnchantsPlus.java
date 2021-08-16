package com.dsfhdshdjtsb.EnchantsPlus;

import com.dsfhdshdjtsb.EnchantsPlus.effects.RampageEffect;
import com.dsfhdshdjtsb.EnchantsPlus.enchantments.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class EnchantsPlus implements ModInitializer {
	public static final Enchantment DUELING = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("enchantsp", "dueling"),
			new DuelingEnchantment()
	);

	public static final Enchantment LETHALITY = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("enchantsp", "lethality"),
			new LethalityEnchantment()
	);

	public static final Enchantment TRIUMPH = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("enchantsp", "triumph"),
			new TriumphEnchantment()
	);

	public static final Enchantment RAMPAGE = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("enchantsp", "rampage"),
			new RampageEnchantment()
	);

	public static final Enchantment INFERNO = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("enchantsp", "inferno"),
			new InfernoEnchantment()
	);

	public static final Enchantment SORCERY = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("enchantsp", "sorcery"),
			new SorceryEnchantment()
	);

	public static final StatusEffect RAMPAGE_EFFECT = new RampageEffect();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		Registry.register(Registry.STATUS_EFFECT, new Identifier("enchantsp", "rampage"), RAMPAGE_EFFECT);


	}
}
