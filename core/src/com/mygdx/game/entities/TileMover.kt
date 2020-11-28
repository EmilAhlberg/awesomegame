package com.mygdx.game.entities

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.GridPoint2
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.World
import com.mygdx.game.visual.GameAssets
import kotlin.math.abs

abstract class TileMover(world: World): GameObject(world) {

    enum class MoveAction(val vec: Vector2, val point: GridPoint2) {
        IDLE(Vector2(0f,0f), GridPoint2(0,0)),
        LEFT(Vector2(-1f, 0f), GridPoint2(-1,0)),
        RIGHT(Vector2(1f, 0f), GridPoint2(1,0)),
        UP(Vector2(0f,1f), GridPoint2(0,1)),
        DOWN(Vector2(0f,-1f), GridPoint2(0,-1))
    }

    var targetTile = currentTile.cpy()

    var currentMove = MoveAction.IDLE
    var nextMove = MoveAction.IDLE

    var speed = 50f
    var dx: Float = 0f
    var dy: Float = 0f
    var rectangle : Rectangle = Rectangle(0f,0f, TILE_WIDTH, TILE_HEIGHT)


    override fun update(bottomLeftTile: GridPoint2, topRightTile: GridPoint2) {
        super.update(bottomLeftTile, topRightTile)
        setOrientation()
        handleMovement()
        rectangle.setPosition(position)
    }

    private fun handleMovement() {
        if(currentMove == MoveAction.IDLE) {
            return
        }
        if(isPathBlocked()) {
            return
        }
        //cpy performance?
        var nextPos = position.cpy().mulAdd(currentMove.vec, speed*Gdx.graphics.deltaTime)

        var tileDx= (targetTile.x* TILE_WIDTH).toInt() != nextPos.x.toInt()
        var tileDY = (targetTile.y* TILE_WIDTH).toInt() != nextPos.y.toInt()
        if(tileDx || tileDY) {
            // Tile move transition until target is reached.
            position = nextPos
            return
        }
        //Finish movement
        currentTile.add(currentMove.point)
        prepareMovement(nextMove)
        applySmoothing(nextPos)
    }

    private fun isPathBlocked(): Boolean{
        if(world.isBlocked(targetTile)){
            prepareMovement(nextMove)
            //make sure position is reset
            position.set(currentTile.x* TILE_WIDTH, currentTile.y* TILE_HEIGHT)
            return true
        }
        return false
    }

    fun moveToCell(x: Int, y: Int) {
        currentTile.set(x,y)
        position.set(currentTile.x* TILE_WIDTH, currentTile.y* TILE_HEIGHT)
    }


    private fun applySmoothing(nextPos: Vector2) {
        // Smoothing of movement action transitions
        nextPos.sub(position)
        position.set(currentTile.x* TILE_WIDTH, currentTile.y* TILE_HEIGHT)
        position.mulAdd(currentMove.vec, abs(nextPos.x + nextPos.y))
    }

    private fun setOrientation() {
        var newMove = MoveAction.IDLE
        if(abs(dx) > abs(dy)) {
            newMove = if (dx < 0) MoveAction.LEFT else MoveAction.RIGHT
        } else if(dy != 0f) {
            newMove = if (dy < 0) MoveAction.DOWN else MoveAction.UP
        }

        if(currentMove == MoveAction.IDLE){
            prepareMovement(newMove)
        }
        nextMove = newMove
    }

    private fun prepareMovement(nextMove: MoveAction) {
        currentMove = nextMove
        animation = GameAssets.animationMap[assetId+currentMove.toString().toLowerCase()]!!
        targetTile.set(currentTile).add(currentMove.point)
    }
}