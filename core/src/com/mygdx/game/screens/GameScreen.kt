package com.mygdx.game.screens

import com.badlogic.gdx.*
import com.badlogic.gdx.graphics.FPSLogger
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.mygdx.game.GameAssets
import com.mygdx.game.World
import com.mygdx.game.controls.ControllerFactory
import com.mygdx.game.entities.Player
import com.mygdx.game.visual.Asset

class GameScreen (game: Game) : Screen   {
    val game : Game = game
    val stage : Stage = Stage(ScreenViewport())
    private var camera: OrthographicCamera  = stage.getViewport().getCamera() as OrthographicCamera

    var secondFromStart = 0f

    var world : World

    val debugMoveBox: Rectangle = Rectangle(Gdx.graphics.width*0.80.toFloat(), 0f,
    Gdx.graphics.width.toFloat()*0.20.toFloat(), Gdx.graphics.height*0.5.toFloat())

    val region: TextureRegion = TextureRegion(GameAssets.getTexture(Asset.PLAYER), debugMoveBox.x, debugMoveBox.y,
            debugMoveBox.width, debugMoveBox.height)

    private val endY = 600
    private val minAltitude = 0.5f

    private val maxAltitude = 2.5f
    private var percent = 0f
    lateinit var player : Player

    var batch: SpriteBatch = SpriteBatch();

    init {
        //batch = SpriteBatch();
        world = World();
    }

    override fun show() {
        Gdx.app.log("MainScreen", "show")
        player = Player(Asset.PLAYER)
        Gdx.input.inputProcessor = ControllerFactory.create(player)
        System.out.println(Gdx.graphics.height)
        System.out.println(Gdx.graphics.width)
        //stage.addListener()
    }

    override fun render(delta: Float) {
        FPSLogger().log()
        secondFromStart += 0.1f
        percent = secondFromStart % 5 / 5
        percent = Math.cos(percent * Math.PI * 2).toFloat() / 2 + 0.5f
        percent = Math.cos(percent * Math.PI * 2).toFloat() / 2 + 0.5f
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch.begin()
            batch.draw(region, debugMoveBox.x, debugMoveBox.y, debugMoveBox.width, debugMoveBox.height)
        //moveCamera()
        stage.act(Gdx.graphics.getDeltaTime())
        player.update()
        player.draw(batch, 0.5f)
        stage.draw()
        batch.end()
    }

    private fun moveCamera() {
        val currentX =  percent
        val currentY = percent
        val percentZ = Math.abs(percent - 0.5f) * 2
        val currentZ = maxAltitude - (maxAltitude - minAltitude) * percentZ
        camera!!.position.x = currentX
        camera!!.position.y = currentY
        camera!!.zoom = currentZ
        camera!!.update()
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