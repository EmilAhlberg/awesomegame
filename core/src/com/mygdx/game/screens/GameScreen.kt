package com.mygdx.game.screens

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.FPSLogger
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.mygdx.game.World
import com.mygdx.game.controls.ControllerFactory
import com.mygdx.game.entities.Player
import com.mygdx.game.visual.Asset
import kotlin.math.abs

class GameScreen (game: Game) : Screen   {
    val game : Game = game
    val stage : Stage = Stage(ScreenViewport())

    private var world : World
    var gameTime = 0f
    var batch: SpriteBatch = SpriteBatch();


    init {
        world = World(stage.viewport.camera as OrthographicCamera, batch)
    }

    override fun show() {
        Gdx.app.log("MainScreen", "show")
    }

    override fun render(delta: Float) {
        gameTime += Gdx.graphics.deltaTime
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch.begin()
        world.draw(batch, delta)
        batch.end()
    }

    override fun hide() {
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun dispose() {
        stage.dispose()
    }
}