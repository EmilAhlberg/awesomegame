package com.mygdx.game.entities

abstract class Mover(asset: String) : GameObject(asset){

    var direction = 0
    var speed = 100f


    abstract fun move(x: Float, y: Float);

}