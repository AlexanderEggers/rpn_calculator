package org.demo.calculator.opertator

import java.util.*

class StackOperation(private val value: String) : Operation(OperationType.STACK) {

    override fun onExecute(stack: LinkedList<Double>, values: Array<Double>): Boolean {
        /**
         * Converts [String] to Double.
         */
        val doubleValue = value.toDoubleOrNull()

        /**
         * If the doubleValue is null, the [String] is not numeric.
         */
        if (doubleValue != null) {
            /**
             * Adds the converted [Double] to the stack.
             */
            stack.addLast(doubleValue)
            return true
        }
        return false
    }
}