package input

import java.math.RoundingMode
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
        val df = DecimalFormat("0.##########")
        df.roundingMode = RoundingMode.DOWN
        return df.format(value)
    }
}
