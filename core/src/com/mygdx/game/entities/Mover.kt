package com.mygdx.game.entities

abstract class Mover(asset: String) : GameObject(asset){

    var rotation = 0
    var speed = 1f


    abstract fun move(x: Float, y: Float);

}