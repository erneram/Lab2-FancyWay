package org.example

abstract class Operator(symbol: String): Expression {
    abstract fun apply(a: Double, b: Double ):Double
}