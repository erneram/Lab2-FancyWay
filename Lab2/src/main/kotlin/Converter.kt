package org.example

import java.util.*

class Converter {
    private val operators = mapOf(
        "+" to Plus(),
        "-" to Minus(),
        "*" to Multiplication(),
        "/" to Division(),
        "^" to Power()
    )

    fun inToPost(expression: String): String {
        val stack = Stack<Char>()
        val result = StringBuilder()
        val operators = setOf('+', '-', '*', '/', '^')

        var i = 0
        while (i < expression.length) {
            val char = expression[i]

            when {
                char.isWhitespace() -> i++
                char.isDigit() -> {
                    while (i < expression.length && expression[i].isDigit()) {
                        result.append(expression[i])
                        i++
                    }
                    result.append(' ')  // separete numbers
                }
                char == '(' -> {
                    stack.push(char)
                    i++
                }
                char == ')' -> {
                    while (stack.isNotEmpty() && stack.peek() != '(') {
                        result.append(stack.pop()).append(' ')
                    }
                    if (stack.isNotEmpty() && stack.peek() == '(') {
                        stack.pop()  // Remove '(' from stack
                    } else {
                        throw IllegalArgumentException("Unbalanced parentheses in expression")
                    }
                    i++
                }
                operators.contains(char) -> {
                    while (stack.isNotEmpty() && precedence(stack.peek()) >= precedence(char)) {
                        result.append(stack.pop()).append(' ')
                    }
                    stack.push(char)
                    i++
                }
                else -> i++  // In case of unexpected character
            }
        }

        while (stack.isNotEmpty()) {
            val top = stack.pop()
            if (top == '(' || top == ')') {
                throw IllegalArgumentException("Unbalanced parentheses in expression")
            }
            result.append(top).append(' ')
        }

        return result.toString().trim()
    }

    private fun precedence(operator: Char): Int {
        return when (operator) {
            '+', '-' -> 1
            '*', '/' -> 2
            '^' -> 3
            else -> -1
        }
    }

    fun postEvaluation(expression: String): Double {
        val stack = mutableListOf<Double>()

        for (token in expression.split(" ")) {
            when {
                token.isDouble() -> stack.add(token.toDouble())
                operators.containsKey(token) -> {
                    if (stack.size < 2) {
                        throw IllegalArgumentException("No hay suficientes operandos para la operación")
                    }
                    val b = stack.removeAt(stack.lastIndex)
                    val a = stack.removeAt(stack.lastIndex)
                    val result = operators[token]!!.apply(a, b)
                    stack.add(result)
                }
            }
        }

        if (stack.size != 1) {
            throw IllegalArgumentException("La expresión postfix es inválida")
        }

        return stack[0]
    }

    private fun String.isDouble() = this.toDoubleOrNull() != null
}
