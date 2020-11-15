package com.mygdx.game.entities

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.MathUtils.random
import com.mygdx.game.visual.AnimationType
import com.mygdx.game.visual.Asset
import com.mygdx.game.visual.GameAssets

class Sheep : TileMover() {

    init {
        assetId = Asset.SHEEP.id
        animation = GameAssets.animationMap[assetId + AnimationType.RUNNING.id]!!
    }

    override fun move() {

        dx = random.nextFloat()*100

        x += dx * speed * Gdx.graphics.deltaTime
        y += dy * speed * Gdx.graphics.deltaTime
    }
}