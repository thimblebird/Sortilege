package net.lyof.sortilege.particles.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.lyof.sortilege.particles.amo.ParticleShaders;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import org.jetbrains.annotations.Nullable;

public class WispParticle extends SpriteBillboardParticle {
    public WispParticle(ClientWorld clientWorld, double x, double y, double z) {
        super(clientWorld, x, y, z);
    }

    public WispParticle(ClientWorld clientWorld, double x, double y, double z, SpriteProvider sprite, float r, float g, float b) {
        super(clientWorld, x, y, z);

        this.setSprite(sprite);

        this.setColor(r, g, b);
        this.gravityStrength = 0;
        this.velocityMultiplier = 0f;
        this.maxAge = 10;

        this.velocityX = 0;
        this.velocityY = 0;
        this.velocityZ = 0;
    }

    @Override
    public void tick() {
        float ratio = (float) (this.maxAge - this.age) / this.maxAge;
        this.setAlpha(ratio);
        super.tick();
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleShaders.PARTICLE_SHEET_ADDITIVE_MULTIPLY;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider sprites;

        public Factory(SpriteProvider spriteProvider) {
            this.sprites = spriteProvider;
        }

        @Nullable
        @Override
        public Particle createParticle(DefaultParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new WispParticle(world, x, y, z, this.sprites, (float) velocityX, (float) velocityY, (float) velocityZ);
        }
    }
}
