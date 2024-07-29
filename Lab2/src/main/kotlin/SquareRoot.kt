package org.example

import kotlin.math.sqrt

class SquareRoot : Operator("sqrt"){
    override fun precedence() = 3

    override fun apply(a: Double, b: Double) = sqrt(a)

    override fun toString() = "sqrt"

}