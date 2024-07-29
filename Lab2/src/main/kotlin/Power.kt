package org.example

import kotlin.math.pow

class Power : Operator("^") {
    override fun precedence() = 3

    override fun apply(a: Double, b: Double) = a.pow(b)

}