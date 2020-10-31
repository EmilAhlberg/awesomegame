package com.mygdx.game.controls

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.input.GestureDetector
import com.mygdx.game.entities.Player
import java.lang.Exception

class ControllerFactory () {

    companion object {
        fun create(player: Player) : InputProcessor {
            val platformType = Gdx.app.type
            if(platformType == Application.ApplicationType.Android)  {
                return GestureDetector(AndroidPlayerController(player))
            }
            if(platformType == Application.ApplicationType.Desktop) {
                return DesktopPlayerController(player)
            }
            throw Exception()
        }
    }
}