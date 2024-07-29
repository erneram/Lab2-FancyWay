package org.example

class Multiplication : Operator("*"){
    override fun precedence() = 2

    override fun apply(a: Double, b: Double) = a * b

}