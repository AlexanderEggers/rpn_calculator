package logic

import logic.Operator.*
import java.text.DecimalFormat

class OperatorBackend {

    fun calculateTwoValueOperations(value1: Double, value2: Double, operator: Operator): String {
        val result: Double = when(operator) {
            ADDITION -> value1 + value2
            SUBTRACTION -> value1 - value2
            MULTIPLICATION -> value1 * value2
            DIVISION -> value1 / value2
            else -> {
                Double.NaN
            }
        }

        val format = DecimalFormat("0.###############")
        return format.format(result)
    }

    fun calculateOneValueOperations(value1: Double, operator: Operator): String {
        val result: Double = when(operator) {
            SQUARE -> Math.sqrt(value1)
            else -> {
                Double.NaN
            }
        }

        val format = DecimalFormat("0.###############")
        return format.format(result)
    }
}