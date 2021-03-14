package com.mygdx.game.visual

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.ParticleEffect
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool
import com.badlogic.gdx.graphics.g2d.SpriteBatch


class PooledEffectHandler {
    private val effectPool: ParticleEffectPool
    private val effects: ArrayList<ParticleEffectPool.PooledEffect> = ArrayList();

    init {
        val effect = ParticleEffect();
        effect.load(Gdx.files.internal("particle.p"), GameAssets.particleAtlas)

        //If your particle effect includes additive or pre-multiplied particle emitters
        //you can turn off blend function clean-up to save a lot of draw calls, but
        //remember to switch the Batch back to GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA
        //before drawing "regular" sprites or your Stage.
        //effect.setEmittersCleanUpBlendFunction(false);


        //Set up the particle effect that will act as the pool's template
        effectPool = ParticleEffectPool(effect, 2, 5);
        effects.add(effectPool.obtain())
    }

    fun reset() {
        // Reset all effects:
        effects.forEach { e -> e.free() } //free all the effects back to the pool
        effects.clear(); //clear the current effects array
    }

    fun spawnAt(x: Float, y: Float) {
        var effect = effectPool.obtain()
        effect.setPosition(x, y)
        effects.add(effect)
        effect.start()
        effect.setDuration(100)
    }


    fun draw(batch: SpriteBatch, delta: Float) {
        // Update and draw effects:
        val iterator = effects.iterator()
        val removerCache = ArrayList<ParticleEffect>()

        for (effect in iterator) {
            effect.draw(batch, delta)
            if(effect.isComplete){
                effect.free()
                removerCache.add(effect)
            }
        }

        for (effect in removerCache){
            effects.remove(effect)
        }

    }
}