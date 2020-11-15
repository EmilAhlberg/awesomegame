package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TiledMapRenderer
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.mygdx.game.controls.ControllerFactory
import com.mygdx.game.entities.Player
import com.mygdx.game.entities.Sheep
import kotlin.math.abs
import kotlin.math.sign


class World(camera: OrthographicCamera, player: Player) {

    private val camera : OrthographicCamera = camera!!
    private val player = player
    private val sheep = Sheep()
    private val tiledMap: TiledMap
    private val tiledMapRenderer: TiledMapRenderer

    init {
        Gdx.input.inputProcessor = ControllerFactory.create(player)
        camera.position.x = player.x
        camera.position.y = player.y

        tiledMap = TmxMapLoader().load("tilemap.tmx")
        tiledMapRenderer = OrthogonalTiledMapRenderer(tiledMap)


    }


    fun draw(batch: Batch, gameTime: Float) {
        moveCamera()
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        player.update()
        sheep.update()

        if (player.rectangle.overlaps(sheep.rectangle)) {
            sheep.x -= 250f
        }

        sheep.draw(batch, gameTime)
        player.draw(batch, gameTime)
    }

    private fun moveCamera() {
        //example camera implementation
        val diff = 100
        val dx =  player.x - camera.position.x
        val dy =  player.y - camera.position.y
        if(abs(dx) > diff) {
            camera.position.x += sign(dx)*player.speed
        }
        if(abs(dy) > diff) {
            camera.position.y += sign(dy)*player.speed
        }
        camera!!.update()
    }
}