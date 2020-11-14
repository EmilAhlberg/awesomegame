package com.mygdx.game.entities

import com.badlogic.gdx.Gdx
import com.mygdx.game.visual.AnimationType
import com.mygdx.game.visual.Asset
import com.mygdx.game.visual.GameAssets

class Player : Mover(Asset.PLAYER) {


    override fun move() {
        x += dx * speed * Gdx.graphics.deltaTime
        y += dy * speed * Gdx.graphics.deltaTime
    }


    fun updateMovement(dx: Float, dy: Float) {
        //too many assigns probably
        animation = GameAssets.animationMap[assetId + AnimationType.RUNNING.id]!!
        this.dx = dx
        this.dy = dy
    }

    fun resetMovement() {
        animation = GameAssets.animationMap[assetId + AnimationType.IDLE.id]!!
        dx = 0f
        dy = 0f
    }
}