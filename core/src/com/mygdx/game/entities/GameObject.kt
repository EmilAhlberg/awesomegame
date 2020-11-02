package com.mygdx.game.entities

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.mygdx.game.visual.GameAssets
import com.mygdx.game.visual.GameSprite

abstract class GameObject(asset: String) {
    private var sprite: Animation<TextureRegion>? = GameAssets.map[asset]



    var x = 400f
    var y = 300f

    init {
    }


    fun draw(batch: Batch, parentAlpha: Float, gameTime: Float) {
        batch.draw(sprite?.getKeyFrame(gameTime), x, y)
    }

}