package org.example

fun main() {
    val converter = Converter()
    println("calcu")
    var cycle = true
    while (cycle) {
        print("ingrese su cadena a evaluar: ")
        val expression = readLine()!!
        if (expression.lowercase() == "exit") {
            cycle = false
        } else {
            println("Expresión ingresada: $expression")
            val postFix = converter.inToPost(expression)
            println("Postfix: $postFix")
            val result = converter.postEvaluation(postFix)
            println("Result: $result")
        }
    }
}