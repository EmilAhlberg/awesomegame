package com.mygdx.game

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.mygdx.game.visual.Asset

class GameAssets {

    companion object {
        val manager : AssetManager = AssetManager()

        init {
            loadAssets()
        }

        private fun loadAssets() {
            var playerAsset = AssetDescriptor(Asset.PLAYER, Texture::class.java)

            manager.load(playerAsset)

            manager.finishLoading()
        }

        fun getTexture(asset: String) : Texture {
            return manager.get(asset)
        }
    }



}