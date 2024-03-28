package com.dsfhdshdjtsb.CombatEnchants;

import com.dsfhdshdjtsb.CombatEnchants.config.ModConfigs;
import com.dsfhdshdjtsb.CombatEnchants.effects.*;
import com.dsfhdshdjtsb.CombatEnchants.enchantments.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;


public class CombatEnchants implements ModInitializer {
	public static final EquipmentSlot[] ALL_ARMOR = new EquipmentSlot[] {EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
	public static final String MOD_ID = "cenchants";

	//I could make this alphabetical, or I could not
	public static Enchantment DUELING;
	public static Enchantment LETHALITY;
	public static Enchantment TRIUMPH;
	public static Enchantment RAMPAGE;
	public static Enchantment INFERNO;
	public static Enchantment SORCERY;
	public static Enchantment LIFESTEAL;
	public static Enchantment ZAP;
	public static Enchantment VOLLEY;
	public static Enchantment HUNTER;
	public static Enchantment TRANQUILIZE;
	public static Enchantment FROST;
	public static Enchantment SHIELDING;
	public static Enchantment SELFDESTRUCT;
	public static Enchantment FLAME_WALKER;
	public static Enchantment VISION;
	public static Enchantment DARKNESS;
	public static Enchantment ANTIHEAL;
	public static Enchantment INSPIRE;
	public static Enchantment REJUVENATE;
	public static Enchantment BITE;
	public static Enchantment HOOK;
	public static Enchantment PERCEPTION;
	public static Enchantment COMBO;
	public static Enchantment KNOCKUP;
	public static Enchantment FERVOR;
	public static Enchantment BARRAGE;
	public static Enchantment DEFLECT;
	public static Enchantment SNAP;
	public static Enchantment STEADFAST;
	public static Enchantment LIFELINE;
	public static Enchantment INKING;
	public static Enchantment GRAB;
	public static Enchantment TREMOR;
	public static Enchantment AFTERSHOCK;
	public static Enchantment SHIELDBREAK;

	public static final DefaultParticleType SHIELD_PARTICLE = FabricParticleTypes.simple();
	public static final DefaultParticleType SLEEPY_PARTICLE = FabricParticleTypes.simple();

	public static final RegistryEntry<StatusEffect> RAMPAGE_EFFECT = register("rampage", new RampageEffect());
	public static final RegistryEntry<StatusEffect> LIFESTEAL_COOLDOWN_EFFECT = register("lifesteal_cooldown", new LifestealCooldownEffect());
	public static final RegistryEntry<StatusEffect> MARK_EFFECT = register("mark", new MarkEffect());
	public static final RegistryEntry<StatusEffect> SLEEPY_EFFECT = register("sleepy", new SleepyEffect());
	public static final RegistryEntry<StatusEffect> SHIELDING_COOLDOWN_EFFECT = register("shielding_cooldown", new ShieldingCooldown());
	public static final RegistryEntry<StatusEffect> FIRE_WALK_EFFECT = register("fire_walk", new FireWalkEffect());
	public static final RegistryEntry<StatusEffect> ANTIHEAL_EFFECT = register("antiheal", new AntihealEffect());
	public static final RegistryEntry<StatusEffect> DELAYED_DEATH_EFFECT = register("delayed_death", new DelayedDeathEffect());
	public static final RegistryEntry<StatusEffect> FERVOR_EFFECT = register("fervor", new FervorEffect());
	public static final RegistryEntry<StatusEffect> BARRAGE_EFFECT = register("barrage", new BarrageEffect());
	public static final RegistryEntry<StatusEffect> BARRAGE_STACK_EFFECT = register("barrage_stack", new BarrageStackEffect());
	public static final RegistryEntry<StatusEffect> LIFELINE_COOLDOWN_EFFECT = register("lifeline_cooldown", new LifelineCooldownEffect());
	public static final RegistryEntry<StatusEffect> FROST_PARTICLE_EFFECT = register("frost_particle", new FrostParticleEffect());
	public static final RegistryEntry<StatusEffect> SLEEPY_PARTICLE_EFFECT = register("sleepy_particle", new SleepyParticleEffect());
	public static final RegistryEntry<StatusEffect> GRAB_EFFECT = register("grab", new GrabEffect());
	public static final RegistryEntry<StatusEffect> AFTERSHOCK_EFFECT = register("aftershock", new AftershockEffect());
	public static final RegistryEntry<StatusEffect> LIGHTNING_IMMUNE = register("lightning_immune", new LightningImmuneEffect());




	@Override
	public void onInitialize() {
		ModConfigs.registerConfigs();

		ANTIHEAL = new AntihealEnchantment();
		BARRAGE = new BarrageEnchantment();
		BITE = new BiteEnchantment();
		COMBO = new ComboEnchantment();
		DARKNESS = new DarknessCurse();
		DEFLECT = new DeflectEnchantment();
		DUELING = new DuelingEnchantment();
		FERVOR = new FervorEnchantment();
		FLAME_WALKER = new FlameWalkerEnchantment();
		FROST = new FrostEnchantment();
		GRAB = new GrabEnchantment();
		HOOK = new HookEnchantment();
		HUNTER = new HunterEnchantment();
		INFERNO = new InfernoEnchantment();
		INKING = new InkingEnchantment();
		INSPIRE = new InspireEnchantment();
		KNOCKUP = new KnockupEnchantment();
		LETHALITY = new LethalityEnchantment();
		LIFELINE = new LifelineEnchantment();
		LIFESTEAL = new LifestealEnchantment();
		PERCEPTION = new PerceptionEnchantment();
		RAMPAGE = new RampageEnchantment();
		REJUVENATE = new RejuvenateEnchantment();
		SHIELDING = new ShieldingEnchantment();
		AFTERSHOCK = new AftershockEnchantment();
		SNAP = new SnapEnchantment();
		SORCERY = new SorceryEnchantment();
		SELFDESTRUCT = new SelfDestructEnchantment();
		TRANQUILIZE = new TranquilizeEnchantment();
		TREMOR = new TremorEnchantment();
		TRIUMPH = new TriumphEnchantment();
		VISION = new VisionEnchantment();
		VOLLEY = new VolleyEnchantment();
		ZAP = new ZapEnchantment();

	}

	private static RegistryEntry<StatusEffect> register(String id, StatusEffect statusEffect) {
		return Registry.registerReference(Registries.STATUS_EFFECT, new Identifier(id), statusEffect);
	}
}
