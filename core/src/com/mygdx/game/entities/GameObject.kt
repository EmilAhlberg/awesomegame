package com.mygdx.game.entities

import com.badlogic.gdx.graphics.g2d.Batch
import com.mygdx.game.visual.AnimationType
import com.mygdx.game.visual.GameAssets
import kotlin.math.PI

abstract class GameObject(asset: String) {
    val assetId = asset
    protected var animation = GameAssets.animationMap[assetId + AnimationType.IDLE.id]!!

    var x = 400f
    var y = 300f
    //val width = animation.getKeyFrame(0.1f).regionWidth
    //val height = animation.getKeyFrame(0.1f).regionHeight


    var rotation = 0f



    fun draw(batch: Batch, gameTime: Float) {
        val frame = animation.getKeyFrame(gameTime)
        batch.draw(frame, x, y, frame.regionWidth / 2f, frame.regionHeight / 2f,
            frame.regionWidth.toFloat(), frame.regionHeight.toFloat(), 1f, 1f, 180 + rotation*180/ PI.toFloat())

        batch.draw(frame, 100f, 100f)
        batch.draw(frame, 100f, 600f)
    }
}