package com.mygdx.game.entities

import kotlin.math.PI
import kotlin.math.atan2

abstract class Mover(asset: String) : GameObject(asset){

    var speed = 1f
    var dx: Float = 0f
    var dy: Float = 0f

    abstract fun move();


    fun update() {
        rotation = atan2(this.dy, this.dx)  + PI.toFloat()/2 % 2f * PI.toFloat()
        move()
    }

}