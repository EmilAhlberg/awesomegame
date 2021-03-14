package com.mygdx.game.controls

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.input.GestureDetector
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.entities.Player
import com.mygdx.game.visual.GameCamera

class AndroidPlayerController (player: Player) : GestureDetector.GestureListener {

    val player: Player = player
    private val moveControlBox: Rectangle = Rectangle(Gdx.graphics.width*0.75.toFloat(), Gdx.graphics.height.toFloat()*0.5.toFloat(),
    Gdx.graphics.width.toFloat()*0.2.toFloat(), Gdx.graphics.height*0.5.toFloat())
    private val moveBoxCenter: Vector2 = moveControlBox.getCenter(Vector2())

    var alreadyMoving: Boolean = false

    private fun movePlayer(x: Float, y: Float): Boolean {
        val dx = x - moveBoxCenter.x
        // inverted y-axis translation
        val dy = moveBoxCenter.y - y
        player.updateMovement(dx, dy)
        return true
    }

    override fun fling(velocityX: Float, velocityY: Float, button: Int): Boolean {
        return true
    }

    override fun zoom(initialDistance: Float, distance: Float): Boolean {
        GameCamera.zoom = distance/initialDistance
        return true
    }

    override fun pan(x: Float, y: Float, deltaX: Float, deltaY: Float): Boolean {
        if(!alreadyMoving) {
            alreadyMoving = true
            moveBoxCenter.set(x, y)
        }
        return movePlayer(x, y)
    }

    override fun pinchStop() {
    }

    override fun tap(x: Float, y: Float, count: Int, button: Int): Boolean {
        return true
    }

    override fun panStop(x: Float, y: Float, pointer: Int, button: Int): Boolean {
        alreadyMoving = false
        player.resetMovement()
        return true
    }

    override fun longPress(x: Float, y: Float): Boolean {
        return true
    }

    override fun touchDown(x: Float, y: Float, pointer: Int, button: Int): Boolean {
        return true
    }

    override fun pinch(initialPointer1: Vector2?, initialPointer2: Vector2?, pointer1: Vector2?, pointer2: Vector2?): Boolean {
        return true
    }

}