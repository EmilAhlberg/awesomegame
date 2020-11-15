package com.mygdx.game.entities

import com.badlogic.gdx.Gdx
import com.mygdx.game.visual.AnimationType
import com.mygdx.game.visual.Asset
import com.mygdx.game.visual.GameAssets

class Player() : TileMover() {

    init {
        assetId = Asset.PLAYER.id
        animation = GameAssets.animationMap[assetId + AnimationType.IDLE.id]!!
    }

    fun updateMovement(dx: Float, dy: Float) {
        //probably need to throttle assigns
        animation = GameAssets.animationMap[assetId + AnimationType.IDLE.id]!!
        this.dx = dx
        this.dy = dy
    }

    fun resetMovement() {
        animation = GameAssets.animationMap[assetId + AnimationType.IDLE.id]!!
        dx = 0f
        dy = 0f
    }
}