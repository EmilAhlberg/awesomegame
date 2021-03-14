package com.mygdx.game.entities

import com.mygdx.game.World
import com.mygdx.game.visual.Asset
import com.mygdx.game.visual.GameAssets

class Player(world: World) : TileMover(world) {

    init {
        assetId = Asset.PLAYER.id
        animation = GameAssets.animationMap[assetId + MoveAction.IDLE.toAnimationString()]!!
        spawnAt(88, 79)
    }

    fun updateMovement(dx: Float, dy: Float) {
        this.dx = dx
        this.dy = dy
    }

    fun resetMovement() {
        dx = 0f
        dy = 0f
    }
}