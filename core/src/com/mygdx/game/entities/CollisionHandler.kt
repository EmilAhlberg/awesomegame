package com.mygdx.game.entities

class CollisionHandler {

    companion object{

        fun handleCollisions(movers: ArrayList<TileMover>) {
            // n*log(n) of actives!
            val sortedActives = movers.filter { mover -> mover.isActive }.sortedBy{ a -> a.position.x}
            // efficient n^2 of actives
            for(i in sortedActives.indices) {
                val current = sortedActives[i]
                for (j in i+1..sortedActives.size) {
                    val collider = sortedActives[j]
                    // arbitrary assumption; 2 < tile difference is maximum mover size
                    if(current.currentTile.x + 2 < collider.currentTile.x) {
                        //utilize sort
                        break
                    }
                    checkCollision(current, collider)
                }
            }
        }

        private fun checkCollision(current: TileMover, collider: TileMover) {
            if(current.currentTile.equals(collider.currentTile)) {
                println("colliders: $current, $collider")
            }
        }
    }
}