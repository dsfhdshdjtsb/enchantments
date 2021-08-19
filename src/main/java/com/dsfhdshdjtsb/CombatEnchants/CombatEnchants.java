package com.dsfhdshdjtsb.CombatEnchants;

import com.dsfhdshdjtsb.CombatEnchants.effects.LifestealCooldwon;
import com.dsfhdshdjtsb.CombatEnchants.effects.MarkEffect;
import com.dsfhdshdjtsb.CombatEnchants.effects.RampageEffect;
import com.dsfhdshdjtsb.CombatEnchants.enchantments.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class CombatEnchants implements ModInitializer {
	public static final Enchantment DUELING = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("cenchants", "dueling"),
			new DuelingEnchantment()
	);

	public static final Enchantment LETHALITY = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("cenchants", "lethality"),
			new LethalityEnchantment()
	);

	public static final Enchantment TRIUMPH = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("cenchants", "triumph"),
			new TriumphEnchantment()
	);

	public static final Enchantment RAMPAGE = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("cenchants", "rampage"),
			new RampageEnchantment()
	);

	public static final Enchantment INFERNO = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("cenchants", "inferno"),
			new InfernoEnchantment()
	);

	public static final Enchantment SORCERY = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("cenchants", "sorcery"),
			new SorceryEnchantment()
	);

	public static final Enchantment LIFESTEAL = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("cenchants", "lifesteal"),
			new LifestealEnchantment()
	);

	public static final Enchantment ZAP = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("cenchants", "zap"),
			new ZapEnchantment()
	);

	public static final Enchantment VOLLEY = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("cenchants", "volley"),
			new VolleyEnchantment()
	);

	public static final Enchantment HUNTER = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("cenchants", "hunter"),
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
		Registry.register(Registry.STATUS_EFFECT, new Identifier("cenchants", "rampage"), RAMPAGE_EFFECT);
		Registry.register(Registry.STATUS_EFFECT, new Identifier("cenchants", "lifesteal_cooldown"), LIFESTEAL_COOLDOWN_EFFECT);
		Registry.register(Registry.STATUS_EFFECT, new Identifier("cenchants", "mark"), MARK_EFFECT);

	}
}
