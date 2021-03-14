package com.mygdx.game.entities

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.GridPoint2
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
        DOWN(Vector2(0f,-1f), GridPoint2(0,-1));

        open fun toAnimationString() :String = this.toString().toLowerCase()
    }

    var targetTile: GridPoint2 = currentTile.cpy()

    var previousMove = MoveAction.IDLE
    var currentMove = MoveAction.IDLE
    var nextMove = MoveAction.IDLE

    var speed = 50f
    var dx: Float = 0f
    var dy: Float = 0f


    override fun update() {
        super.update()
        setOrientation()
        handleMovement()
    }

    fun handleCollision() {
        world.effectPoolHandler.spawnAt(position.x, position.y)
        currentTile.sub(previousMove.point)
        position.set(currentTile.x * TILE_WIDTH, currentTile.y * TILE_HEIGHT)
        prepareMoveAction(MoveAction.IDLE)
    }


    private fun handleMovement() {
        if(currentMove == MoveAction.IDLE || isPathBlocked()) {
            return
        }
        //cpy performance?
        var updatedPosition = position.cpy().mulAdd(currentMove.vec, speed * Gdx.graphics.deltaTime)

        var tileDx= (targetTile.x* TILE_WIDTH).toInt() != updatedPosition.x.toInt()
        var tileDy = (targetTile.y* TILE_WIDTH).toInt() != updatedPosition.y.toInt()
        if(tileDx || tileDy) {
            // Tile move transition until target is reached.
            position = updatedPosition
            return
        }
        //Finish movement
        currentTile.add(currentMove.point)
        prepareMoveAction(nextMove)
        applySmoothing(updatedPosition)
    }

    private fun isPathBlocked(): Boolean{
        val isBlocked = world.isBlocked(targetTile)
        if(isBlocked) {
            prepareMoveAction(nextMove)
            //make sure position is reset
            position.set(currentTile.x * TILE_WIDTH, currentTile.y * TILE_HEIGHT)
        }
        return isBlocked
    }

    /** REMOVE? */
    fun spawnAt(x: Int, y: Int) {
        currentTile.set(x, y)
        targetTile.set(currentTile)
        currentMove = nextMove
        position.set(currentTile.x * TILE_WIDTH, currentTile.y * TILE_HEIGHT)
    }

    /** Smoothing of movement action transitions */
    private fun applySmoothing(updatedPosition: Vector2) {
        updatedPosition.sub(position)
        position.set(currentTile.x * TILE_WIDTH, currentTile.y * TILE_HEIGHT)
        position.mulAdd(currentMove.vec, abs(updatedPosition.x + updatedPosition.y))
    }

    /** Evaluate the orientation of the eventual MoveAction, undertaken by the TileMover. */
    private fun setOrientation() {
        var newMove = MoveAction.IDLE
        if(abs(dx) > abs(dy)) {
            newMove = if (dx < 0) MoveAction.LEFT else MoveAction.RIGHT
        } else if(dy != 0f) {
            newMove = if (dy < 0) MoveAction.DOWN else MoveAction.UP
        }

        if(currentMove == MoveAction.IDLE){
            prepareMoveAction(newMove)
        }
        nextMove = newMove
    }

    /** Setup the animation and target tile, for a MoveAction. */
    private fun prepareMoveAction(nextMove: MoveAction) {
        previousMove = currentMove
        currentMove = nextMove
        animation = GameAssets.animationMap[assetId + currentMove.toAnimationString()]!!
        targetTile.set(currentTile).add(currentMove.point)
    }

    fun isMoving(): Boolean = currentMove != MoveAction.IDLE
}