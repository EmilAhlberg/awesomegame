package com.mygdx.game.entities

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2


class Player(asset: String) : Mover(asset) {

    var hasMoved: Boolean = false
    var dx: Float = 0f
    var dy: Float = 0f

    override fun move(dx: Float,dy: Float) {
        this.x += this.dx * speed * Gdx.graphics.deltaTime
        this.y += this.dy * speed * Gdx.graphics.deltaTime
        hasMoved = true
    }

    fun update() {
        move()
        hasMoved = false
    }

    fun updateMovement(dx: Float, dy: Float) {
        this.dx = dx
        this.dy = dy
    }

    fun resetMovement() {
        dx = 0f
        dy = 0f
    }

    private fun move(){
        move(0f,0f)
    }

    fun updateRotation() {
        /*
        if (rotateByPosition) {
            val rotationVector = Vector2(position.x - prevPosition.x, position.y - prevPosition.y)
            rotationRad = rotationVector.angleRad()
            rotationDeg = rotationRad * MathUtils.radiansToDegrees
        }
        */
    }




}