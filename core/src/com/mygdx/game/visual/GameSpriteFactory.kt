package com.mygdx.game.visual

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureRegion

class GameSpriteFactory {
/*
    companion object {
        fun create(asset: String): GameSprite {

            val map = HashMap<String, TextureRegion>()





/*

            //simple case
            map.put("default", GameAssets.getTexture(asset))
  */
            val sprite = Sprite( GameAssets.getTexture(asset))
            return sprite
        }
    }
*/
}
