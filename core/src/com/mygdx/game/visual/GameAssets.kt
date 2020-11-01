package com.mygdx.game.visual

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture

class GameAssets {

    companion object {
        val manager : AssetManager = AssetManager()

        init {
            loadAssets()
        }

        private fun loadAssets() {
            var playerAsset = AssetDescriptor(Asset.PLAYER, Texture::class.java)
            var playerSheetAsset = AssetDescriptor(Asset.PLAYER_SHEET, Texture::class.java)
            manager.load(playerAsset)
            manager.load(playerSheetAsset)

            manager.finishLoading()
        }

        fun getTexture(asset: String) : Texture {
            return manager.get(asset)
        }
    }



}