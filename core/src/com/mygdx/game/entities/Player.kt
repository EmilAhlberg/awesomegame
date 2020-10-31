package com.mygdx.game.entities

import com.badlogic.gdx.Gdx

class Player(asset: String) : Mover(asset) {

    var hasMoved: Boolean = false

    override fun move(dx: Float,dy: Float) {
        System.out.println(dx)
        this.x += dx*speed * Gdx.graphics.deltaTime
        this.y += dy*speed * Gdx.graphics.deltaTime
        hasMoved = true
    }

    fun update() {
        hasMoved = false
    }




}