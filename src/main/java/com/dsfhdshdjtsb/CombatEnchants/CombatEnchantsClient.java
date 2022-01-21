package com.dsfhdshdjtsb.CombatEnchants;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.particle.DamageParticle;
import net.minecraft.client.particle.EmotionParticle;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class CombatEnchantsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register(((atlasTexture, registry) -> {
            registry.register(new Identifier("cenchants", "particle/shield"));
            registry.register(new Identifier("cenchants", "particle/sleepy"));
        }));

        ParticleFactoryRegistry.getInstance().register(CombatEnchants.SHIELD_PARTICLE, EmotionParticle.AngryVillagerFactory::new);
        ParticleFactoryRegistry.getInstance().register(CombatEnchants.SLEEPY_PARTICLE, EmotionParticle.AngryVillagerFactory::new);
    }

}
