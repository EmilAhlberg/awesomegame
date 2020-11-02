package com.mygdx.game.visual

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion

class GameAssets {

    companion object {
        val manager : AssetManager = AssetManager()
        lateinit var textureAtlas: TextureAtlas
        val map = HashMap<String, Animation<TextureRegion>>()

        init {
            loadAssets()
        }

        private fun loadAssets() {
            textureAtlas =  TextureAtlas(Gdx.files.internal("pack.atlas"))
            map[Asset.PLAYER] = Animation<TextureRegion>(0.1f,  textureAtlas.findRegions("playerrunning"),
                Animation.PlayMode.LOOP)
            manager.finishLoading()
        }
    }
}