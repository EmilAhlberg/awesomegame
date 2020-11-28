package com.mygdx.game.entities

class TileProperties() {

    enum class Properties(val type: String, val id: Int) {
        BLOCKED("blocked", 1),
        DOWN("down", 2);


        companion object{
            private val propFromType = values().associateBy(Properties::type)
            private val propFromId = values().associateBy(Properties::id)

            fun fromType(type: String) = propFromType[type]
            fun fromId(id: Int) = propFromId[id]
        }
    }

    companion object{
    }
}