package com.mygdx.game

import java.util.*
import kotlin.math.exp

class RandomHelper {

    companion object{

        private val random = Random()

        fun poisson(mean: Float): Int {
            var r = 0
            var a = random.nextFloat()
            var p = exp(-mean)
            while (a > p) {
                r++
                a -= p
                p = p * mean / r
            }
            return r
        }

        fun nextFloat(): Float = random.nextFloat()
    }
}