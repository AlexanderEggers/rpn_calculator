package input

import java.text.DecimalFormat

object Utils {

    val STACK_SEPERATOR: String = " "

    fun isNumeric(str: String): Boolean {
        try {
            java.lang.Double.parseDouble(str)
        } catch (nfe: NumberFormatException) {
            return false
        }

        return true
    }

    fun parseToDouble(str: String): Double {
        return java.lang.Double.parseDouble(str)
    }

    fun formatDouble(value: Double): String {
        return DecimalFormat("0.##########").format(value)
    }
}
