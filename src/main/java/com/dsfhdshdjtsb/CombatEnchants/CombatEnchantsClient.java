package com.dsfhdshdjtsb.CombatEnchants;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.EmotionParticle;
import net.minecraft.client.particle.EndRodParticle;

public class CombatEnchantsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        ParticleFactoryRegistry.getInstance().register(CombatEnchants.SHIELD_PARTICLE, EndRodParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(CombatEnchants.SLEEPY_PARTICLE, EmotionParticle.AngryVillagerFactory::new);
    }

}
