package org.example

abstract class Operator(val symbol: String) : Expression {
    abstract fun apply(a: Double, b: Double): Double

    override fun toString() = symbol
}
