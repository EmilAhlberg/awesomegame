package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.maps.MapLayer
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TiledMapRenderer
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.math.GridPoint2
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.controls.ControllerFactory
import com.mygdx.game.entities.*
import kotlin.math.abs
import kotlin.math.sign


class World(camera: OrthographicCamera, player: Player) {

    private val camera : OrthographicCamera = camera!!
    private val player = player
    private val tiledMap: TiledMap
    private val tiledMapRenderer: TiledMapRenderer
    private val movers: ArrayList<TileMover> = ArrayList()

    private var bottomLeftTile: GridPoint2 = GridPoint2()

    private var topRightTile: GridPoint2 = GridPoint2()

    init {
        Gdx.input.inputProcessor = ControllerFactory.create(player)

        movers.add(player)
        //movers.add(Sheep())

        camera.position.x = player.position.x
        camera.position.y = player.position.y
        camera.zoom = 0.5f
        tiledMap = TmxMapLoader().load("collision.tmx")
        tiledMapRenderer = OrthogonalTiledMapRenderer(tiledMap)
        CollisionHandler.initBlockingTiles(tiledMap)


    }


    fun draw(batch: Batch, gameTime: Float) {
        moveCamera()
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        //culling opportunities
        movers.forEach { mover -> mover.update(bottomLeftTile, topRightTile)}
        //TODO:
        //CollisionHandler.handleCollisions(movers)
        movers.forEach{mover -> mover.draw(batch, gameTime)}
    }


    private fun moveCamera() {
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

    }
}