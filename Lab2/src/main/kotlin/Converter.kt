package org.example

class Converter {
    private val operators = mapOf(
        "+" to Plus(),
        "-" to Minus(),
        "*" to Multiplication(),
        "/" to Division(),
        "^" to Power()
    )

    fun inToPost(expression: String): String {
        val output = mutableListOf<String>()
        val operatorStack = mutableListOf<Operator>()

        for (token in expression.split(" ")) {
            when {
                token.isDouble() -> output.add(token)
                operators.containsKey(token) -> {
                    val operator = operators[token]!!
                    while (operatorStack.isNotEmpty() && operatorStack.last().precedence() >= operator.precedence()) {
                        output.add(operatorStack.removeAt(operatorStack.lastIndex).toString())
                    }
                    operatorStack.add(operator)
                }
                token == "(" -> operatorStack.add(object : Operator("(") {
                    override fun precedence() = Int.MIN_VALUE
                    override fun apply(a: Double, b: Double) = throw UnsupportedOperationException()
                })
                token == ")" -> {
                    while (operatorStack.isNotEmpty() && operatorStack.last().toString() != "(") {
                        output.add(operatorStack.removeAt(operatorStack.lastIndex).toString())
                    }
                    if (operatorStack.isNotEmpty()) operatorStack.removeAt(operatorStack.lastIndex) // Remove the "("
                }
            }
        }

        while (operatorStack.isNotEmpty()) {
            output.add(operatorStack.removeAt(operatorStack.lastIndex).toString())
        }

        return output.joinToString(" ")
    }

    fun postEvaluation(expression : String ): Double {
        val output = mutableListOf<Double>()

        for (token in expression.split(" ")) {
            when {
                token.isDouble() -> output.add(token.toDouble())
                operators.containsKey(token) -> {
                    val b = output.removeAt(output.lastIndex)
                    val a = output.removeAt(output.lastIndex)
                    val result = operators[token]!!.apply(a, b)
                    output.add(result)
                }
            }
        }
        return output[0]
    }

    private fun String.isDouble() = this.toDoubleOrNull() != null
}
