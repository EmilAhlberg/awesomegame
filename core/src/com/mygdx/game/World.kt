package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.GridPoint2
import com.mygdx.game.controls.ControllerFactory
import com.mygdx.game.entities.*
import com.mygdx.game.visual.GameCamera
import com.mygdx.game.visual.PooledEffectHandler


class World(ortoCamera: OrthographicCamera, batch: Batch) {

    private val player = Player(this)
    private var levels: ArrayList<Level> = ArrayList()
    val camera = GameCamera(ortoCamera, player.getCenteredPosition())
    var effectPoolHandler = PooledEffectHandler()


    companion object{
        var gameTime = 0f
    }

    init {
        Gdx.input.inputProcessor = ControllerFactory.create(player)
        levels.add(Level(player, batch, "Map1.tmx", this))
        //levels.add(Level(player, batch, "Non-infinite.tmx"))
    }

    fun draw(batch: Batch, delta: Float) {
        gameTime += Gdx.graphics.deltaTime
        camera.update(player.getCenteredPosition(), player.speed)
        levels[Level.currentLevel].setView(camera.orthographicCamera);
        levels[Level.currentLevel].update()
        levels[Level.currentLevel].draw(batch, delta)
        effectPoolHandler.draw(batch as SpriteBatch, delta)
    }

    fun isBlocked(gridPoint: GridPoint2): Boolean = levels[Level.currentLevel].collisionHandler.isBlocked(gridPoint)
}