package com.mygdx.game.entities

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.visual.AnimationType
import com.mygdx.game.visual.GameAssets
import kotlin.math.PI

abstract class GameObject() {
    lateinit var assetId: String
    lateinit var animation: Animation<TextureRegion>

    var position = Vector2(0f, 0f)


    var rotation = 0f


    fun draw(batch: Batch, gameTime: Float) {
        val frame = animation.getKeyFrame(gameTime)
        batch.draw(frame, position.x, position.y, frame.regionWidth / 2f, frame.regionHeight / 2f,
            frame.regionWidth.toFloat(), frame.regionHeight.toFloat(), 1f, 1f, rotation*180/ PI.toFloat())
    }
}