package com.mygdx.game.entities

import com.mygdx.game.visual.AnimationType
import com.mygdx.game.visual.Asset
import com.mygdx.game.visual.GameAssets

class Sheep : TileMover() {

    init {
        assetId = Asset.PLAYER.id
        animation = GameAssets.animationMap[assetId + AnimationType.RUNNING.id]!!
    }
}