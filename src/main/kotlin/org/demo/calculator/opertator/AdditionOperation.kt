package org.demo.calculator.opertator

import java.util.*

/**
 * Default addition operation which requires two elements from the stack and can be reverted.
 */
class AdditionOperation : Operation(OperationType.ADDITION) {

    override fun onExecute(stack: LinkedList<Double>, values: Array<Double>): Boolean {
        stack.addLast(values[0].plus(values[1]))
        return true
    }
}