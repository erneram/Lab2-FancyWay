package org.example

class Division : Operator("/") {
    override fun precedence() = 2


    override fun apply(a: Double, b: Double) = a / b
}