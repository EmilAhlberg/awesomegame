package com.mygdx.game

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TiledMapRenderer
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.math.GridPoint2
import com.mygdx.game.entities.CollisionHandler
import com.mygdx.game.entities.Player
import com.mygdx.game.entities.Sheep
import com.mygdx.game.entities.TileMover
import kotlin.collections.ArrayList

class Level(player: Player, batch: Batch, level: String, world: World)  {

    private val tiledMap: TiledMap = TmxMapLoader().load(level)
    private val tiledMapRenderer: TiledMapRenderer
    private val movers: ArrayList<TileMover> = ArrayList()
    private var drawnLayers = 12
    var collisionHandler = CollisionHandler()

    companion object{
        var currentLevel = 0
    }

    init {
        tiledMapRenderer = OrthogonalTiledMapRenderer(tiledMap, batch)
        collisionHandler.initTilePropertyMatrix(tiledMap)
        movers.add(player)

        val sheep = Sheep(world)
        movers.add(sheep)
        val sheep2 = Sheep(world)
        movers.add(sheep2)
        val sheep3 = Sheep(world)
        movers.add(sheep3)
    }


    fun update(bottomLeftTile: GridPoint2, topRightTile: GridPoint2) {
        movers.forEach { mover -> mover.update(bottomLeftTile, topRightTile)}
    }


    fun draw(batch: Batch, gameTime: Float) {
        //culling opportunities
        //TODO:
        //CollisionHandler.handleCollisions(movers)

        repeat(drawnLayers) {i -> tiledMapRenderer.renderTileLayer(tiledMap.layers.get(i) as TiledMapTileLayer)}

        if(movers[0].currentTile.x == 83 && movers[0].currentTile.y == 78) {
            drawnLayers = 8
        }

        if(movers[0].currentTile.x == 83 && movers[0].currentTile.y == 77) {
            drawnLayers = 12
        }

        //.layers.forEach{layer -> tiledMapRenderer.renderTileLayer(layer as TiledMapTileLayer)}
        movers.forEach{mover -> mover.draw(batch, gameTime)}


        if (movers[0].currentTile.x == 3 && currentLevel == 10) {
            currentLevel++
        }
    }


    fun setView(camera: OrthographicCamera) {
        tiledMapRenderer.setView(camera)
    }
}