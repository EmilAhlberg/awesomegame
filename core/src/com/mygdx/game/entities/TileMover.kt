package com.mygdx.game.entities

import com.badlogic.gdx.math.Rectangle
import kotlin.math.abs

abstract class TileMover: GameObject() {

    val IDLE = 0f
    val UP = 1f
    val DOWN = 2f
    val LEFT = 3f
    val RIGHT = 4f

    var orientation = IDLE

    var speed = 10f
    var dx: Float = 0f
    var dy: Float = 0f
    var rectangle : Rectangle = Rectangle(0f,0f,
        100f, 100f)

    abstract fun move();


    fun update() {
        setOrientation()
        moveTile()
        //rotation = atan2(this.dy, this.dx)  + PI.toFloat()/2 % 2f * PI.toFloat()
        //move()
        rectangle.setPosition(x,y)
    }

    private fun moveTile() {
        when(orientation){
            LEFT -> x -= speed
            RIGHT -> x += speed
            UP -> y += speed
            DOWN -> y -= speed
        }
    }

    private fun setOrientation() {
        var newOrientation = IDLE
        if(abs(dx) > abs(dy)) {
            newOrientation = if (dx < 0) LEFT else RIGHT
        } else if(dy != 0f) {
            newOrientation = if (dy < 0) DOWN else UP
        }
        orientation = newOrientation
    }
}