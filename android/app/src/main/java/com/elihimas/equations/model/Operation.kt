package com.elihimas.equations.model

enum class Operation {
    Sum, Subtraction, Multiplication;

    override fun toString() = when (this) {
        Sum -> "+"
        Subtraction -> "-"
        Multiplication -> "x"
    }
}
