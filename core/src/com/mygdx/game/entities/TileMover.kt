package com.mygdx.game.entities

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.GridPoint2
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import kotlin.math.abs

abstract class TileMover: GameObject() {

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
        //cpy performance?
        var nextPos = position.cpy().mulAdd(currentMove.vec, speed*Gdx.graphics.deltaTime)

        var xMismatch= (targetTile.x* TILE_WIDTH).toInt() != nextPos.x.toInt()
        var yMismatch = (targetTile.y* TILE_WIDTH).toInt() != nextPos.y.toInt()

        if(xMismatch || yMismatch ) {
            // Tile move transition
            position = nextPos
            return
        }
        //Finish movement
        currentTile.add(currentMove.point)
        prepareMovement(nextMove)
        applySmoothing(nextPos)
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
        targetTile.add(currentMove.point)
    }
}