package org.demo.calculator.opertator

import java.util.*

/**
 * Default subtraction operation which requires two elements from the stack and can be reverted.
 */
class SubtractionOperation : Operation(OperationType.SUBTRACTION) {

    override fun onExecute(stack: LinkedList<Double>, values: Array<Double>): Boolean {
        stack.addLast(values[0].minus(values[1]))
        return true
    }
}