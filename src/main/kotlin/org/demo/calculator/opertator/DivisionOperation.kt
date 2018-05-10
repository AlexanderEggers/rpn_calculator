package org.demo.calculator.opertator

import java.util.*

/**
 * Default division operation which requires two elements from the stack and can be reverted.
 */
class DivisionOperation : Operation(OperationType.DIVISION) {

    override fun onExecute(stack: LinkedList<Double>, values: Array<Double>): Boolean {
        /**
         * Checks if the second operation value not null, which would return a NaN.
         */
        if (values[1] != 0.0) {
            stack.addLast(values[0] / values[1])
            return true
        }
        return false
    }
}