package com.dsfhdshdjtsb.CombatEnchants;

import com.dsfhdshdjtsb.CombatEnchants.effects.*;
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

	public static final Enchantment TRANQUILIZE = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("cenchants", "tranquilize"),
			new TranquilizeEnchantment()
	);

	public static final Enchantment FROST = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("cenchants", "frost"),
			new FrostEnchantment()
	);

	public static final Enchantment SHEILDING = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("cenchants", "shielding"),
			new ShieldingEnchantment()
	);

	public static final Enchantment SUICIDE = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("cenchants", "suicide"),
			new SuicideEnchantment()
	);

	public static final Enchantment FLAME_WALKER = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("cenchants", "flame_walker"),
			new FlameWalkerEnchantment()
	);

	public static final Enchantment VISION = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("cenchants", "vision"),
			new VisionEnchantment()
	);

	public static final Enchantment VAMPIRISM = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("cenchants", "vampirism"),
			new VampirismCurse()
	);

	public static final Enchantment ANTIHEAL = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("cenchants", "antiheal"),
			new AntihealEnchantment()
	);

	public static final StatusEffect RAMPAGE_EFFECT = new RampageEffect();
	public static final StatusEffect LIFESTEAL_COOLDOWN_EFFECT = new LifestealCooldownEffect();
	public static final StatusEffect MARK_EFFECT = new MarkEffect();
	public static final StatusEffect SLEEPY_EFFECT = new SleepyEffect();
	public static final StatusEffect SHIELDING_COOLDOWN_EFFECT = new ShieldingCooldown();
	public static final StatusEffect FIRE_WALK_EFFECT = new FireWalkEffect();
	public static final StatusEffect ANTIHEAL_EFFECT = new AntihealEffect();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		Registry.register(Registry.STATUS_EFFECT, new Identifier("cenchants", "rampage"), RAMPAGE_EFFECT);
		Registry.register(Registry.STATUS_EFFECT, new Identifier("cenchants", "lifesteal_cooldown"), LIFESTEAL_COOLDOWN_EFFECT);
		Registry.register(Registry.STATUS_EFFECT, new Identifier("cenchants", "mark"), MARK_EFFECT);
		Registry.register(Registry.STATUS_EFFECT, new Identifier("cenchants", "sleepy"), SLEEPY_EFFECT);
		Registry.register(Registry.STATUS_EFFECT, new Identifier("cenchants", "shielding_cooldown"), SHIELDING_COOLDOWN_EFFECT);
		Registry.register(Registry.STATUS_EFFECT, new Identifier("cenchants", "fire_walk"), FIRE_WALK_EFFECT);
		Registry.register(Registry.STATUS_EFFECT, new Identifier("cenchants", "antiheal"), ANTIHEAL_EFFECT);

	}
}
