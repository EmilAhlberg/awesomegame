package com.mygdx.game.controls

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.input.GestureDetector
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.entities.Player

class AndroidPlayerController (player: Player) : GestureDetector.GestureListener {

    val player: Player = player
    val moveControlBox: Rectangle = Rectangle(Gdx.graphics.width*0.80.toFloat(), Gdx.graphics.height.toFloat()*0.5.toFloat(),
    Gdx.graphics.width.toFloat()*0.20.toFloat(), Gdx.graphics.height*0.5.toFloat())
    val moveBoxCenter: Vector2 = moveControlBox.getCenter(Vector2())
    val dxScaler = (moveControlBox.width/2)
    val dyScaler = (moveControlBox.height/2)


    override fun fling(velocityX: Float, velocityY: Float, button: Int): Boolean {
        return true
    }

    override fun zoom(initialDistance: Float, distance: Float): Boolean {
        return true
    }

    override fun pan(x: Float, y: Float, deltaX: Float, deltaY: Float): Boolean {
        //if in mover rectangle
        System.out.println(x.toString() + " " + y)
        System.out.println(moveControlBox.x.toString() + " " + moveControlBox.y)
        System.out.println((moveControlBox.x + moveControlBox.width ).toString() + " "
                +(moveControlBox.y + moveControlBox.height ).toString())
        if(!moveControlBox.contains(x,y)) {
            System.out.println("HIT!!")
            return true
        }

        if(!player.hasMoved) {
            System.out.println("MOVE!!")
            val dx = moveBoxCenter.x-x
            val dy = moveBoxCenter.y-y
            System.out.println(dx)
            System.out.println(dy)

            player.move(dy/dyScaler - 0.5f, dx/dxScaler - 0.5f)
        }
        return true
    }

    override fun pinchStop() {
    }

    override fun tap(x: Float, y: Float, count: Int, button: Int): Boolean {
        return true
    }

    override fun panStop(x: Float, y: Float, pointer: Int, button: Int): Boolean {
        return true
    }

    override fun longPress(x: Float, y: Float): Boolean {
        return true
    }

    override fun touchDown(x: Float, y: Float, pointer: Int, button: Int): Boolean {
        //player.move(x, y)
        return true
    }

    override fun pinch(initialPointer1: Vector2?, initialPointer2: Vector2?, pointer1: Vector2?, pointer2: Vector2?): Boolean {
        return true
    }

}