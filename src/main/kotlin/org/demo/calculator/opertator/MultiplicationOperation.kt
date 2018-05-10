package org.demo.calculator.opertator

import java.util.*

/**
 * Default multiplication operation which requires two elements from the stack and can be reverted.
 */
class MultiplicationOperation : Operation(OperationType.MULTIPLICATION) {

    override fun onExecute(stack: LinkedList<Double>, values: Array<Double>): Boolean {
        stack.addLast(values[0] * values[1])
        return true
    }
}