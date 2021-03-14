package com.mygdx.game.entities

import com.badlogic.gdx.math.GridPoint2
import com.mygdx.game.RandomHelper
import com.mygdx.game.World
import com.mygdx.game.visual.Asset
import com.mygdx.game.visual.GameAssets

class Sheep(world: World) : TileMover(world) {

    private var lastMoveTime = World.gameTime
    private val poissonMean = 1.1f
    private var moveCap = RandomHelper.poisson(poissonMean)

    init {
        assetId = Asset.SHEEP.id
        animation = GameAssets.animationMap[assetId + MoveAction.IDLE.toAnimationString()]!!
        spawnAt(89, 75)
        speed = 25f
    }


    override fun update() {
        dx = 0f
        dy = 0f
        if (World.gameTime - moveCap > lastMoveTime) {
            lastMoveTime = World.gameTime
            moveCap = RandomHelper.poisson(poissonMean)
            when (RandomHelper.nextFloat()) {
                in 0.75f..1f -> dx = 1f
                in 0.5f..75f -> dx = -1f
                in 0.25f..0.5f -> dy = 1f
                in 0.0f..0.25f -> dy = -1f
            }
        }

        super.update()
    }
}