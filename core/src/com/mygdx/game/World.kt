package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.GridPoint2
import com.mygdx.game.controls.ControllerFactory
import com.mygdx.game.entities.*
import kotlin.math.abs
import kotlin.math.sign


class World(camera: OrthographicCamera, batch: Batch) {

    private val camera: OrthographicCamera = camera!!
    private val player = Player(this)
    private var levels: ArrayList<Level> = ArrayList()

    private var bottomLeftTile: GridPoint2 = GridPoint2()
    private var topRightTile: GridPoint2 = GridPoint2()


    companion object{
        var zoom = 0.5f
        var gameTime = 0f
    }

    init {
        Gdx.input.inputProcessor = ControllerFactory.create(player)
        levels.add(Level(player, batch, "Map1.tmx", this))
        //levels.add(Level(player, batch, "Non-infinite.tmx"))
        camera.position.x = player.position.x
        camera.position.y = player.position.y
        camera.zoom = zoom
    }

    fun draw(batch: Batch, gameTime: Float) {
        Companion.gameTime += Gdx.graphics.deltaTime
        moveCamera()
        levels[Level.currentLevel].update(bottomLeftTile, topRightTile)
        levels[Level.currentLevel].draw(batch, gameTime)
    }


    private fun moveCamera() {
        camera.zoom = zoom
        //example camera implementation
        val diff = 100
        //uugh
        val dx =  player.position.x + GameObject.TILE_WIDTH/2 - camera.position.x
        val dy =  player.position.y + 3*GameObject.TILE_HEIGHT/2 - camera.position.y
        if(abs(dx) > diff) {
            camera.position.x += sign(dx)*player.speed*Gdx.graphics.deltaTime
        }
        if(abs(dy) > diff) {
            camera.position.y += sign(dy)*player.speed*Gdx.graphics.deltaTime
        }
        camera.update()

        //uugh2
        bottomLeftTile.set(((camera.position.x - camera.viewportWidth/2) / GameObject.TILE_WIDTH).toInt(),
            ((camera.position.y - camera.viewportHeight/2) / GameObject.TILE_HEIGHT).toInt())
        topRightTile.set(bottomLeftTile.x + (camera.viewportWidth / GameObject.TILE_WIDTH).toInt(),
                (bottomLeftTile.y + (camera.viewportHeight / GameObject.TILE_HEIGHT).toInt()))
        levels[Level.currentLevel].setView(camera);
    }

    fun isBlocked(gridPoint: GridPoint2): Boolean = levels[Level.currentLevel].collisionHandler.isBlocked(gridPoint)

}