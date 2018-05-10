package org.demo.calculator.opertator

import java.util.*

/**
 * Default square operation which requires one element from the stack and can be reverted.
 */
class SquareOperation : Operation(OperationType.SQUARE) {

    override fun onExecute(stack: LinkedList<Double>, values: Array<Double>): Boolean {
        stack.addLast(Math.sqrt(values[0]))
        return true
    }
}