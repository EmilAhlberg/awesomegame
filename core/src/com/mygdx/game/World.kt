package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Batch
import com.mygdx.game.controls.ControllerFactory
import com.mygdx.game.entities.Player
import com.mygdx.game.visual.Asset
import kotlin.math.abs

class World(camera: OrthographicCamera, player: Player) {

    private val camera : OrthographicCamera = camera!!
    private val player = player

    init {
        Gdx.input.inputProcessor = ControllerFactory.create(player)
        camera.position.x = player.x
        camera.position.y = player.y
    }


    fun draw(batch: Batch, gameTime: Float) {
        moveCamera()
        player.update()
        player.draw(batch, gameTime)
    }

    private fun moveCamera() {
        //example camera implementation
        val diff = 100
        val dx =  player.x - camera.position.x
        val dy =  player.y - camera.position.y
        if(abs(dx) > diff) {
            camera.position.x += player.dx*player.speed*Gdx.graphics.deltaTime
        }
        if(abs(dy) > diff) {
            camera.position.y += player.dy*player.speed*Gdx.graphics.deltaTime
        }
        camera!!.update()
    }
}