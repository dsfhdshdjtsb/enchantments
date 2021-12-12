package com.dsfhdshdjtsb.CombatEnchants;

import com.dsfhdshdjtsb.CombatEnchants.effects.*;
import com.dsfhdshdjtsb.CombatEnchants.enchantments.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class CombatEnchants implements ModInitializer {
	public static final EquipmentSlot[] ALL_ARMOR = new EquipmentSlot[] {EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
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

	public static final Enchantment DARKNESS = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("cenchants", "darkness"),
			new DarknessCurse()
	);

	public static final Enchantment ANTIHEAL = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("cenchants", "antiheal"),
			new AntihealEnchantment()
	);

	public static final Enchantment INSPIRATION = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("cenchants", "inspiration"),
			new InspirationEnchantment()
	);

	public static final Enchantment RESILIENCE = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("cenchants", "resilience"),
			new ResilienceEnchantment()
	);

	public static final Enchantment AGGRESSION = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("cenchants", "aggression"),
			new AggressionEnchantment()
	);

	public static final Enchantment HOOK = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("cenchants", "hook"),
			new HookEnchantment()
	);

	public static final Enchantment PERCEPTION = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("cenchants", "perception"),
			new PerceptionEnchantment()
	);

	public static final Enchantment COMBO = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("cenchants", "combo"),
			new ComboEnchantment()
	);

	public static final Enchantment KNOCKUP = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("cenchants", "knockup"),
			new KnockupEnchantment()
	);

	public static final Enchantment FERVOR = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("cenchants", "fervor"),
			new FervorEnchantment()
	);

	public static final Enchantment BARRAGE = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("cenchants", "barrage"),
			new BarrageEnchantment()
	);

	public static final Enchantment DEFLECT = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("cenchants", "deflect"),
			new DeflectEnchantment()
	);

	public static final Enchantment SNAP = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("cenchants", "snap"),
			new SnapEnchantment()
	);
	public static final Enchantment STEADFAST = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("cenchants", "steadfast"),
			new SteadfastEnchantment()
	);

	public static final Enchantment REPEL = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("cenchants", "repel"),
			new RepelEnchantment()
	);

	public static final Enchantment LIGHTWEIGHT = Registry.register(
			Registry.ENCHANTMENT,
			new Identifier("cenchants", "lightweight"),
			new LightweightEnchantment()
	);

	public static final StatusEffect RAMPAGE_EFFECT = new RampageEffect();
	public static final StatusEffect LIFESTEAL_COOLDOWN_EFFECT = new LifestealCooldownEffect();
	public static final StatusEffect MARK_EFFECT = new MarkEffect();
	public static final StatusEffect SLEEPY_EFFECT = new SleepyEffect();
	public static final StatusEffect SHIELDING_COOLDOWN_EFFECT = new ShieldingCooldown();
	public static final StatusEffect FIRE_WALK_EFFECT = new FireWalkEffect();
	public static final StatusEffect ANTIHEAL_EFFECT = new AntihealEffect();
	public static final StatusEffect DELAYED_DEATH_EFFECT = new DelayedDeathEffect();
	public static final StatusEffect FERVOR_EFFECT = new FervorEffect();
	public static final StatusEffect BARRAGE_EFFECT = new BarrageEffect();
	public static final StatusEffect BARRAGE_STACK_EFFECT = new BarrageStackEffect();

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
		Registry.register(Registry.STATUS_EFFECT, new Identifier("cenchants", "delayed_death"), DELAYED_DEATH_EFFECT);
		Registry.register(Registry.STATUS_EFFECT, new Identifier("cenchants", "fervor"), FERVOR_EFFECT);
		Registry.register(Registry.STATUS_EFFECT, new Identifier("cenchants", "barrage"), BARRAGE_EFFECT);
		Registry.register(Registry.STATUS_EFFECT, new Identifier("cenchants", "barrage_stack"), BARRAGE_STACK_EFFECT);

		RepelEnchantment.onInitialize();
	}
}
