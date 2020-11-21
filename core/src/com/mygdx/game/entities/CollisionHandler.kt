package com.mygdx.game.entities

import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer
import com.badlogic.gdx.math.GridPoint2

class CollisionHandler {

    companion object{

        private val matrix: Array<BooleanArray> = Array(10) { BooleanArray(10) }

        fun handleCollisions(movers: ArrayList<TileMover>) {
            // n*log(n) of actives!
            val sortedActives = movers.filter { mover -> mover.isActive }.sortedBy{ a -> a.position.x}
            // "efficient n^2" of actives
            for(i in sortedActives.indices) {
                val current = sortedActives[i]
                for (j in i+1 until sortedActives.size) {
                    val collider = sortedActives[j]
                    // arbitrary assumption; 2 < tile difference is maximum mover size
                    if(current.currentTile.x + 2 < collider.currentTile.x) {
                        //utilize sort
                        break
                    }
                    checkCollision(current, collider)
                }
            }
        }

        fun isBlocked(tile: GridPoint2): Boolean{
            println(matrix[tile.x][tile.y])
            return matrix[tile.x][tile.y]
        }

        fun initBlockingTiles(tiledMap: TiledMap) {
            matrix.forEachIndexed {i, row ->
                row.forEachIndexed{j, value ->
                    matrix[i][j] = checkTileProperty(tiledMap, i, j, "blocked")
                }
            }
        }

        //scan layers for property
        private fun checkTileProperty(tiledMap: TiledMap, i: Int, j: Int, property: String): Boolean {
            tiledMap.layers.forEach { layer ->
                val t = (layer as TiledMapTileLayer)
                val hasProperty = t.getCell(i,j)?.tile?.properties?.containsKey(property) ?: false
                if (hasProperty) {
                    return true
                }
            }
            return false
        }

        private fun checkCollision(current: TileMover, collider: TileMover) {
            if(current.currentTile.equals(collider.currentTile)) {
                println("colliders: $current, $collider")
            }
        }
    }
}