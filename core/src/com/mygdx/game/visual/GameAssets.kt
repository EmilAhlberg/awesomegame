package com.mygdx.game.visual

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.mygdx.game.entities.TileMover


class GameAssets {

    companion object {
        private val manager : AssetManager = AssetManager()
        lateinit var textureAtlas: TextureAtlas
        lateinit var particleAtlas: TextureAtlas
        val animationMap = HashMap<String, Animation<TextureRegion>>()

        init {
            loadAssets()
        }

        private fun loadAssets() {
            textureAtlas =  TextureAtlas(Gdx.files.internal("pack.atlas"))
            /*loadAnimation(Asset.PLAYER.id + AnimationType.IDLE.id)
            loadAnimation(Asset.SHEEP.id + AnimationType.IDLE.id)*/

            TileMover.MoveAction.values().forEach {
                loadAnimation(Asset.PLAYER.id + it.toString().toLowerCase()) }
            TileMover.MoveAction.values().forEach {
                loadAnimation(Asset.SHEEP.id + it.toString().toLowerCase()) }

            particleAtlas = TextureAtlas(Gdx.files.internal("particles.atlas"))

            //unnecessary?
            manager.finishLoading()
        }


        private fun loadAnimation(animationId: String) {
            animationMap[animationId] =
                Animation<TextureRegion>(0.3f,  textureAtlas.findRegions(animationId), Animation.PlayMode.LOOP)
        }
    }
}