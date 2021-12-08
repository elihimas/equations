package com.elihimas.equations.business

import com.elihimas.equations.model.Equation
import com.elihimas.equations.model.Game
import com.elihimas.equations.model.Operation
import kotlin.random.Random

object GameCreator {

    fun createGame(): Game {
        val equation = createEquation()
        val correctAnswer = evaluate(equation)
        val shuffledOptions = createShuffledOptions(correctAnswer)

        return Game(equation, shuffledOptions, correctAnswer)
    }

    private fun createEquation(): Equation {
        val operand1 = Random.nextInt(1, 10)
        val operand2 = Random.nextInt(1, 10)
        val operation = Operation.values().random()

        return Equation(operand1, operand2, operation)
    }

    private fun evaluate(equation: Equation): Int {
        return when (equation.operation) {
            Operation.Sum -> equation.operand1 + equation.operand2
            Operation.Subtraction -> equation.operand1 - equation.operand2
            Operation.Multiplication -> equation.operand1 * equation.operand2
        }
    }

    private fun createShuffledOptions(answer: Int): List<Int> {
        val wrongAnswer1 = answer + createRandomEffect()
        val wrongAnswer2 = answer + createRandomEffect()
        val options = listOf(answer, wrongAnswer1, wrongAnswer2)

        return options.shuffled()
    }

    private fun createRandomEffect(): Int {
        var randomEffect = Random.nextInt(-5, 5)
        val isUndesirable = randomEffect == 0

        if (isUndesirable) {
            randomEffect = 1
        }

        return randomEffect
    }
}
