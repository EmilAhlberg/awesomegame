package com.mygdx.game.entities

import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer
import com.badlogic.gdx.math.GridPoint2

class CollisionHandler {

    private val matrix: Array<IntArray> = Array(200) { IntArray(200) }

    fun handleColliders(movers: ArrayList<TileMover>) {
        // n*log(n) of actives!
        val sortedActives = movers.filter { mover -> mover.isActive }.sortedBy { a -> a.position.x }
        // "efficient n^2" of actives
        for (i in sortedActives.indices) {
            val current = sortedActives[i]
            for (j in i + 1 until sortedActives.size) {
                val collider = sortedActives[j]
                // arbitrary assumption; 2 < tile difference is maximum mover size
                if (current.currentTile.x + 2 < collider.currentTile.x) {
                    //utilize sort
                    break
                }
                checkCollision(current, collider)
            }
        }
    }

    fun isBlocked(tile: GridPoint2): Boolean {
        return matrix[tile.x][tile.y] == TileProperties.Properties.BLOCKED.id

    }

    fun initTilePropertyMatrix(tiledMap: TiledMap) {
        matrix.forEachIndexed { i, row ->
            row.forEachIndexed { j, value ->
                matrix[i][j] = checkTileProperty(tiledMap, i, j)
            }
        }
    }

    //scan layers for property
    private fun checkTileProperty(tiledMap: TiledMap, i: Int, j: Int): Int {
        for (layer in tiledMap.layers) {
            (layer as TiledMapTileLayer).getCell(i, j)?.let{cell ->
                for (property in cell.tile.properties.keys) {
                    val tileWithProperty = TileProperties.Properties.fromType(property)
                    tileWithProperty?.let { return tileWithProperty.id }
                }
            }
        }
        return 0
    }

    private fun checkCollision(current: TileMover, collider: TileMover) {
        if (current.currentTile.equals(collider.currentTile)) {
            println("colliders: $current, $collider")
            current.handleCollision()
        }
    }

}

