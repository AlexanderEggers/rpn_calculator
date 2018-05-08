package logic

import java.text.DecimalFormat

object Utils {

    fun isNumeric(str: String): Boolean {
        try {
            java.lang.Double.parseDouble(str)
        } catch (nfe: NumberFormatException) {
            return false
        }

        return true
    }

    fun stringToOperator(operatorString: String): Operator {
        when(operatorString) {
            "+" -> return Operator.ADDITION
            "-" -> return Operator.SUBTRACTION
            "*" -> return Operator.MULTIPLICATION
            "/" -> return Operator.DIVISION
            "sqrt" -> return Operator.SQUARE
            "undo" -> return Operator.UNDO
            "clear" -> return Operator.CLEAR
        }
        return Operator.STACK
    }

    fun parseToDouble(str: String): Double {
        return java.lang.Double.parseDouble(str)
    }

    fun formatDouble(value: Double): String {
        return DecimalFormat("0.###############").format(value)
    }
}
