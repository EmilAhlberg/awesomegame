package com.mygdx.game.entities

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.game.GameAssets

abstract class GameObject(asset: String) {
    var sprite: Sprite = Sprite(GameAssets.getTexture(asset))

    var x = 400f
    var y = 300f


    fun draw(batch: Batch, parentAlpha: Float) {
        batch.draw(sprite, x, y)
    }

}