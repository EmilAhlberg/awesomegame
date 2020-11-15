package com.mygdx.game.entities

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.mygdx.game.visual.AnimationType
import com.mygdx.game.visual.GameAssets
import kotlin.math.PI

abstract class GameObject() {
    lateinit var assetId: String
    lateinit var animation: Animation<TextureRegion>

    var x = 1f
    var y = 1f


    var rotation = 0f


    fun draw(batch: Batch, gameTime: Float) {
        val frame = animation.getKeyFrame(gameTime)
        batch.draw(frame, x, y, frame.regionWidth / 2f, frame.regionHeight / 2f,
            frame.regionWidth.toFloat(), frame.regionHeight.toFloat(), 1f, 1f, 180 + rotation*180/ PI.toFloat())
    }
}