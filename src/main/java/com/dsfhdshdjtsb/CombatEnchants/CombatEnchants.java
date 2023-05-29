package com.dsfhdshdjtsb.CombatEnchants;

import com.dsfhdshdjtsb.CombatEnchants.config.ModConfigs;
import com.dsfhdshdjtsb.CombatEnchants.effects.*;
import com.dsfhdshdjtsb.CombatEnchants.enchantments.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.particle.DefaultParticleType;
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
	public static Enchantment LIGHTWEIGHT;
	public static Enchantment LIFELINE;
	public static Enchantment INKING;
	public static Enchantment GRAB;
	public static Enchantment TREMOR;
	public static Enchantment AFTERSHOCK;
	public static Enchantment SHIELDBREAK;

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
	public static final StatusEffect LIFELINE_COOLDOWN_EFFECT = new LifelineCooldownEffect();
	public static final StatusEffect FROST_PARTICLE_EFFECT = new FrostParticleEffect();
	public static final StatusEffect SLEEPY_PARTICLE_EFFECT = new SleepyParticleEffect();
	public static final StatusEffect GRAB_EFFECT = new GrabEffect();
	public static final StatusEffect AFTERSHOCK_EFFECT = new AftershockEffect();
	public static final StatusEffect LIGHTNING_IMMUNE = new LightningImmuneEffect();

	public static final DefaultParticleType SHIELD_PARTICLE = FabricParticleTypes.simple();
	public static final DefaultParticleType SLEEPY_PARTICLE = FabricParticleTypes.simple();

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
		LIGHTWEIGHT = new LightweightEnchantment();
		PERCEPTION = new PerceptionEnchantment();
		RAMPAGE = new RampageEnchantment();
		REJUVENATE = new RejuvenateEnchantment();
		SHIELDING = new ShieldingEnchantment();
		AFTERSHOCK = new AftershockEnchantment();
		SNAP = new SnapEnchantment();
		SORCERY = new SorceryEnchantment();
		STEADFAST = new SteadfastEnchantment();
		SELFDESTRUCT = new SelfDestructEnchantment();
		TRANQUILIZE = new TranquilizeEnchantment();
		TREMOR = new TremorEnchantment();
		TRIUMPH = new TriumphEnchantment();
		VISION = new VisionEnchantment();
		VOLLEY = new VolleyEnchantment();
		ZAP = new ZapEnchantment();
		SHIELDBREAK = new ShieldBreakEnchantment();


		Registry.register(Registries.STATUS_EFFECT, new Identifier("cenchants", "rampage"), RAMPAGE_EFFECT);
		Registry.register(Registries.STATUS_EFFECT, new Identifier("cenchants", "lifesteal_cooldown"), LIFESTEAL_COOLDOWN_EFFECT);
		Registry.register(Registries.STATUS_EFFECT, new Identifier("cenchants", "mark"), MARK_EFFECT);
		Registry.register(Registries.STATUS_EFFECT, new Identifier("cenchants", "sleepy"), SLEEPY_EFFECT);
		Registry.register(Registries.STATUS_EFFECT, new Identifier("cenchants", "shielding_cooldown"), SHIELDING_COOLDOWN_EFFECT);
		Registry.register(Registries.STATUS_EFFECT, new Identifier("cenchants", "fire_walk"), FIRE_WALK_EFFECT);
		Registry.register(Registries.STATUS_EFFECT, new Identifier("cenchants", "antiheal"), ANTIHEAL_EFFECT);
		Registry.register(Registries.STATUS_EFFECT, new Identifier("cenchants", "delayed_death"), DELAYED_DEATH_EFFECT);
		Registry.register(Registries.STATUS_EFFECT, new Identifier("cenchants", "fervor"), FERVOR_EFFECT);
		Registry.register(Registries.STATUS_EFFECT, new Identifier("cenchants", "barrage"), BARRAGE_EFFECT);
		Registry.register(Registries.STATUS_EFFECT, new Identifier("cenchants", "barrage_stack"), BARRAGE_STACK_EFFECT);
		Registry.register(Registries.STATUS_EFFECT, new Identifier("cenchants", "lifeline_cooldown"), LIFELINE_COOLDOWN_EFFECT);
		Registry.register(Registries.STATUS_EFFECT, new Identifier("cenchants", "frost_particle"), FROST_PARTICLE_EFFECT);
		Registry.register(Registries.STATUS_EFFECT, new Identifier("cenchants", "sleepy_particle"), SLEEPY_PARTICLE_EFFECT);
		Registry.register(Registries.STATUS_EFFECT, new Identifier("cenchants", "grab"), GRAB_EFFECT);
		Registry.register(Registries.STATUS_EFFECT, new Identifier("cenchants", "aftershock"), AFTERSHOCK_EFFECT);
		Registry.register(Registries.STATUS_EFFECT, new Identifier("cenchants", "lightning_immune"), LIGHTNING_IMMUNE);
		Registry.register(Registries.PARTICLE_TYPE, new Identifier("cenchants", "shield_particle"), SHIELD_PARTICLE);
		Registry.register(Registries.PARTICLE_TYPE, new Identifier("cenchants", "sleepy_particle"), SLEEPY_PARTICLE);

	}
}
