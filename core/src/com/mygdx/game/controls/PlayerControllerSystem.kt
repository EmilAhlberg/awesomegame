package com.mygdx.game.controls

import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.input.GestureDetector
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.InputListener

abstract class PlayerControllerSystem() :
    GestureDetector.GestureListener {

    // gesturelistener (android)
    override fun fling(velocityX: Float, velocityY: Float, button: Int): Boolean {
        return true
    }

    override fun zoom(initialDistance: Float, distance: Float): Boolean {
        return true
    }

    override fun pan(x: Float, y: Float, deltaX: Float, deltaY: Float): Boolean {
        return true
    }

    override fun pinchStop() {
    }

    override fun tap(x: Float, y: Float, count: Int, button: Int): Boolean {
        System.out.println("hej3")
        return true
    }

    override fun panStop(x: Float, y: Float, pointer: Int, button: Int): Boolean {
        return true
    }

    override fun longPress(x: Float, y: Float): Boolean {
        return true
    }

    override fun touchDown(x: Float, y: Float, pointer: Int, button: Int): Boolean {
        System.out.println("hej")

        return true
    }

    override fun pinch(initialPointer1: Vector2?, initialPointer2: Vector2?, pointer1: Vector2?, pointer2: Vector2?): Boolean {
        return true
    }
}