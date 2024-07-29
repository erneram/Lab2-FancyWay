package org.example

class Minus : Operator("-"){
    override fun precedence() = 1

    override fun apply(a: Double, b: Double) = a - b
}