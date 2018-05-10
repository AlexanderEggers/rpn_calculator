package org.demo.calculator.input

import java.math.RoundingMode
import java.text.DecimalFormat

/**
 * Class which is used to access certain util methods.
 */
class Utils {

    companion object {

        /**
         * Type of substring which defines the separator between each input value inside the command line.
         */
        const val STACK_SEPARATOR: String = " "

        /**
         * Formats and converts the given [Double] to a [String] object. The [DecimalFormat] ensures that the value is
         * not round and only displays up to ten positions.
         *
         * Note: The given example-2 had the operation "2 sqrt" which resulted into "1.4142135623". I personally would
         * say that the "more" correct value would be "1.4142135624" which would mean that the [DecimalFormat] should
         * round the given [Double] if possible. Due to this example I didn't implement my preferred way.
         *
         * @param value a [Double] object
         * @return a new [String] which has up to ten positions
         */
        fun formatDouble(value: Double): String {
            val df = DecimalFormat("0.##########")
            df.roundingMode = RoundingMode.DOWN
            return df.format(value)
        }
    }
}
