package com.dsfhdshdjtsb.CombatEnchants.config;

import com.dsfhdshdjtsb.CombatEnchants.CombatEnchants;
import com.mojang.datafixers.util.Pair;

public class ModConfigs {
    public static SimpleConfig CONFIG;
    private static ModConfigProvider configs;

    public static boolean AFTERSHOCK;
    public static boolean ANTIHEAL;
    public static boolean BARRAGE;
    public static boolean BITE;
    public static boolean COMBO;
    public static boolean DARKNESS;
    public static boolean DEFLECT;
    public static boolean DUELING;
    public static boolean FERVOR;
    public static boolean FLAMEWALKER;
    public static boolean FROST;
    public static boolean GRAB;
    public static boolean HOOK;
    public static boolean HUNTER;
    public static boolean INFERNO;
    public static boolean INKING;
    public static boolean INSPIRE;
    public static boolean KNOCKUP;
    public static boolean LETHALITY;
    public static boolean LIFELINE;
    public static boolean LIFESTEAL;
    public static boolean LIGHTWEIGHT;
    public static boolean PERCEPTION;
    public static boolean RAMPAGE;
    public static boolean REJUVENATE;
    public static boolean SHIELDING;
    public static boolean SNAP;
    public static boolean SORCERY;
    public static boolean STEADFAST;
    public static boolean SELFDESTRUCT;
    public static boolean TRANQUILIZE;
    public static boolean TREMOR;
    public static boolean TRIUMPH;
    public static boolean VISION;
    public static boolean VOLLEY;
    public static boolean ZAP;

    public static void registerConfigs() {
        configs = new ModConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(CombatEnchants.MOD_ID + "config").provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>("aftershock.enabled", true));
        configs.addKeyValuePair(new Pair<>("antiheal.enabled", true));
        configs.addKeyValuePair(new Pair<>("barrage.enabled", true));
        configs.addKeyValuePair(new Pair<>("bite.enabled", true));
        configs.addKeyValuePair(new Pair<>("combo.enabled", true));
        configs.addKeyValuePair(new Pair<>("darkness.enabled", true));
        configs.addKeyValuePair(new Pair<>("deflect.enabled", true));
        configs.addKeyValuePair(new Pair<>("dueling.enabled", true));
        configs.addKeyValuePair(new Pair<>("fervor.enabled", true));
        configs.addKeyValuePair(new Pair<>("flamewalker.enabled", true));
        configs.addKeyValuePair(new Pair<>("frost.enabled", true));
        configs.addKeyValuePair(new Pair<>("grab.enabled", true));
        configs.addKeyValuePair(new Pair<>("hook.enabled", true));
        configs.addKeyValuePair(new Pair<>("hunter.enabled", true));
        configs.addKeyValuePair(new Pair<>("inferno.enabled", true));
        configs.addKeyValuePair(new Pair<>("inking.enabled", true));
        configs.addKeyValuePair(new Pair<>("inspire.enabled", true));
        configs.addKeyValuePair(new Pair<>("knockup.enabled", true));
        configs.addKeyValuePair(new Pair<>("lethality.enabled", true));
        configs.addKeyValuePair(new Pair<>("lifeline.enabled", true));
        configs.addKeyValuePair(new Pair<>("lifesteal.enabled", true));
        configs.addKeyValuePair(new Pair<>("lightweight.enabled", false));
        configs.addKeyValuePair(new Pair<>("perception.enabled", true));
        configs.addKeyValuePair(new Pair<>("rampage.enabled", true));
        configs.addKeyValuePair(new Pair<>("rejuvenate.enabled", true));
        configs.addKeyValuePair(new Pair<>("shielding.enabled", true));
        configs.addKeyValuePair(new Pair<>("snap.enabled", true));
        configs.addKeyValuePair(new Pair<>("sorcery.enabled", true));
        configs.addKeyValuePair(new Pair<>("steadfast.enabled", false));
        configs.addKeyValuePair(new Pair<>("selfdestruct.enabled", true));
        configs.addKeyValuePair(new Pair<>("tranquilize.enabled", true));
        configs.addKeyValuePair(new Pair<>("tremor.enabled", true));
        configs.addKeyValuePair(new Pair<>("triumph.enabled", true));
        configs.addKeyValuePair(new Pair<>("vision.enabled", true));
        configs.addKeyValuePair(new Pair<>("volley.enabled", true));
        configs.addKeyValuePair(new Pair<>("zap.enabled", true));


    }

    private static void assignConfigs() {
        AFTERSHOCK = CONFIG.getOrDefault("aftershock.enabled", true);
        ANTIHEAL = CONFIG.getOrDefault("antiheal.enabled", true);
        BARRAGE = CONFIG.getOrDefault("barrage.enabled", true);
        BITE = CONFIG.getOrDefault("bite.enabled", true);
        COMBO = CONFIG.getOrDefault("combo.enabled", true);
        DARKNESS = CONFIG.getOrDefault("darkness.enabled", true);
        DEFLECT = CONFIG.getOrDefault("deflect.enabled", true);
        DUELING = CONFIG.getOrDefault("dueling.enabled", true);
        FERVOR = CONFIG.getOrDefault("fervor.enabled", true);
        FLAMEWALKER = CONFIG.getOrDefault("flamewalker.enabled", true);
        FROST = CONFIG.getOrDefault("frost.enabled", true);
        GRAB = CONFIG.getOrDefault("grab.enabled", true);
        HOOK = CONFIG.getOrDefault("hook.enabled", true);
        HUNTER = CONFIG.getOrDefault("hunter.enabled", true);
        INFERNO = CONFIG.getOrDefault("inferno.enabled", true);
        INKING = CONFIG.getOrDefault("inking.enabled", true);
        INSPIRE = CONFIG.getOrDefault("inspire.enabled", true);
        KNOCKUP = CONFIG.getOrDefault("knockup.enabled", true);
        LETHALITY = CONFIG.getOrDefault("lethality.enabled", true);
        LIFELINE = CONFIG.getOrDefault("lifeline.enabled", true);
        LIFESTEAL = CONFIG.getOrDefault("lifesteal.enabled", true);
        LIGHTWEIGHT = CONFIG.getOrDefault("lightweight.enabled", true);
        PERCEPTION = CONFIG.getOrDefault("perception.enabled", true);
        RAMPAGE = CONFIG.getOrDefault("rampage.enabled", true);
        REJUVENATE = CONFIG.getOrDefault("rejuvenate.enabled", true);
        SHIELDING = CONFIG.getOrDefault("shielding.enabled", true);
        SNAP = CONFIG.getOrDefault("snap.enabled", true);
        SORCERY = CONFIG.getOrDefault("sorcery.enabled", true);
        STEADFAST = CONFIG.getOrDefault("steadfast.enabled", true);
        SELFDESTRUCT = CONFIG.getOrDefault("selfdestruct.enabled", true);
        TRANQUILIZE = CONFIG.getOrDefault("tranquilize.enabled", true);
        TREMOR = CONFIG.getOrDefault("tremor.enabled", true);
        TRIUMPH = CONFIG.getOrDefault("triumph.enabled", true);
        VISION = CONFIG.getOrDefault("vision.enabled", true);
        VOLLEY = CONFIG.getOrDefault("volley.enabled", true);
        ZAP = CONFIG.getOrDefault("zap.enabled", true);



        System.out.println("All " + configs.getConfigsList().size() + " have been set properly");
    }
}