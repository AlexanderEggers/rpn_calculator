package org.demo.calculator.opertator

import java.util.*

/**
 * Default division operation which requires two elements from the stack and can be reverted.
 */
class DivisionOperation : Operation(OperationType.DIVISION) {

    override fun onExecute(stack: LinkedList<Double>, values: Array<Double>): Boolean {
        /**
         * Checks if the second operation value equals the value zero, which would return a NaN.
         */
        if (values[1] != 0.0) {
            stack.addLast(values[0].div(values[1]))
            return true
        }
        return false
    }
}