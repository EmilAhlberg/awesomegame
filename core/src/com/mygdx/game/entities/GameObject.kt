package com.mygdx.game.entities

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.GridPoint2
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.World
import com.mygdx.game.entities.GameObject.Companion.TILE_WIDTH
import com.mygdx.game.visual.AnimationType
import com.mygdx.game.visual.GameAssets
import com.mygdx.game.visual.GameCamera
import kotlin.math.PI

abstract class GameObject(world: World) {
    lateinit var assetId: String
    lateinit var animation: Animation<TextureRegion>
    protected val world: World = world
    var isActive = false
    var position = Vector2(0f, 0f)

    var currentTile = GridPoint2(position.x.div(TILE_WIDTH).toInt(), position.y.div(TILE_HEIGHT).toInt())


    var rotation = 0f

    fun getCenteredPosition(): Vector2 = Vector2(TILE_WIDTH, TILE_HEIGHT).scl(0.5f).add(position)

    fun draw(batch: Batch, delta: Float) {
        if (!isActive){
            return
        }

        val frame = animation.getKeyFrame(World.gameTime)
        batch.draw(frame, position.x, position.y, frame.regionWidth / 2f, frame.regionHeight / 2f,
            frame.regionWidth.toFloat(), frame.regionHeight.toFloat(), 1f, 1f, rotation*180/ PI.toFloat())
    }


    open fun update() {
        //very strict culling
        isActive = currentTile.x in GameCamera.bottomLeftTile.x..GameCamera.topRightTile.x &&
                   currentTile.y in GameCamera.bottomLeftTile.y..GameCamera.topRightTile.y
    }



    companion object {
        const val TILE_HEIGHT = 32f
        const val TILE_WIDTH = 32f
    }
}