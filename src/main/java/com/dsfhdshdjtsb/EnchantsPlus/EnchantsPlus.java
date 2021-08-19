package com.dsfhdshdjtsb.EnchantsPlus;

import com.dsfhdshdjtsb.EnchantsPlus.effects.LifestealCooldwon;
import com.dsfhdshdjtsb.EnchantsPlus.effects.MarkEffect;
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

	public static final Enchantment LIFESTEAL = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("enchantsp", "lifesteal"),
			new LifestealEnchantment()
	);

	public static final Enchantment ZAP = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("enchantsp", "zap"),
			new ZapEnchantment()
	);

	public static final Enchantment VOLLEY = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("enchantsp", "volley"),
			new VolleyEnchantment()
	);

	public static final Enchantment HUNTER = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("enchantsp", "hunter"),
			new HunterEnchantment()
	);

	public static final StatusEffect RAMPAGE_EFFECT = new RampageEffect();
	public static final StatusEffect LIFESTEAL_COOLDOWN_EFFECT = new LifestealCooldwon();
	public static final StatusEffect MARK_EFFECT = new MarkEffect();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		Registry.register(Registry.STATUS_EFFECT, new Identifier("enchantsp", "rampage"), RAMPAGE_EFFECT);
		Registry.register(Registry.STATUS_EFFECT, new Identifier("enchantsp", "lifesteal_cooldown"), LIFESTEAL_COOLDOWN_EFFECT);
		Registry.register(Registry.STATUS_EFFECT, new Identifier("enchantsp", "mark"), MARK_EFFECT);

	}
}
