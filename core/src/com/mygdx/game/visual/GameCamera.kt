package com.mygdx.game.visual

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.GridPoint2
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.mygdx.game.entities.GameObject
import kotlin.math.abs
import kotlin.math.sign

class GameCamera(camera: OrthographicCamera, startPosition: Vector2) {

    init {
        camera.position.x = startPosition.x
        camera.position.y = startPosition.y
    }
    private val panThreshold = 100

    companion object{
        var zoom = 0.5f
        val bottomLeftTile: GridPoint2 = GridPoint2()
        val topRightTile: GridPoint2 = GridPoint2()
    }

    val orthographicCamera: OrthographicCamera = camera!!

    fun update(focusedPoint: Vector2, panSpeed: Float){
        moveCamera(Vector3(focusedPoint, 0f), panSpeed)
        updateCameraBounds()
    }

    private fun moveCamera(focusedPoint: Vector3, panSpeed: Float) {
        val cameraOffset = focusedPoint.sub(orthographicCamera.position)
        if(abs(cameraOffset.x) > panThreshold) {
            orthographicCamera.position.x += sign(cameraOffset.x) * panSpeed * Gdx.graphics.deltaTime
        }
        if(abs(cameraOffset.y) > panThreshold) {
            orthographicCamera.position.y += sign(cameraOffset.y) * panSpeed * Gdx.graphics.deltaTime
        }
        orthographicCamera.zoom = zoom
        orthographicCamera.update()
    }


    private fun updateCameraBounds() {
        updateBoundary(bottomLeftTile, -orthographicCamera.viewportWidth, -orthographicCamera.viewportHeight)
        updateBoundary(topRightTile, orthographicCamera.viewportWidth, orthographicCamera.viewportHeight)
    }

    private fun updateBoundary(tile: GridPoint2, horizontalOffset: Float, verticalOffset: Float) {
        tile.set(((orthographicCamera.position.x + horizontalOffset / 2) / GameObject.TILE_WIDTH).toInt(),
            ((orthographicCamera.position.y + verticalOffset / 2) / GameObject.TILE_HEIGHT).toInt())
    }
}