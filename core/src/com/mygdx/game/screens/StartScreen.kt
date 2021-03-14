package com.mygdx.game.screens

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.ScreenViewport


class StartScreen(game: Game) : Screen {
    val game : Game = game
    val stage : Stage = Stage(ScreenViewport())

    init {
        val skin = Skin(Gdx.files.internal("skin/glassy-ui.json"))
        val title = Label("Lorem Ipsum", skin, "big-black")
        title.setAlignment(Align.center)
        title.setY(Gdx.graphics.height * 2 / 3f)
        title.setWidth(Gdx.graphics.width*1.0f)
        stage.addActor(title)

        val playButton = TextButton("Play!", skin)
        playButton.width = Gdx.graphics.width / 2.toFloat()
        playButton.setPosition(Gdx.graphics.width / 2 - playButton.width / 2, Gdx.graphics.height / 2 - playButton.height / 2)
        playButton.addListener(object : InputListener() {
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                game.screen = GameScreen(game)
            }

            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                return true
            }
        })
        stage.addActor(playButton)
    }

    override fun hide() {
    }

    override fun show() {
        Gdx.input.setInputProcessor(stage);
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun dispose() {
        stage.dispose();
    }

}