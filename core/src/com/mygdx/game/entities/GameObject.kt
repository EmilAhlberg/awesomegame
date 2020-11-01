package com.mygdx.game.entities

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.mygdx.game.visual.GameAssets
import com.mygdx.game.visual.GameSprite

abstract class GameObject(asset: String) {
    var sprite: Sprite = Sprite(GameAssets.getTexture(asset)) //SpriteFactory.create()



    var x = 400f
    var y = 300f

    init {
    }


    fun draw(batch: Batch, parentAlpha: Float) {
        batch.draw(sprite, x, y)
    }

}