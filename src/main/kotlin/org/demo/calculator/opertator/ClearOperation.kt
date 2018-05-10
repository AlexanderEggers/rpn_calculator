package org.demo.calculator.opertator

import java.util.*

/**
 * Operation which will be used to remove all elements from the current stack. This operation type cannot be
 * reverted and requires no elements from the stack in order to work.
 */
class ClearOperation : Operation(OperationType.CLEAR) {

    override fun onExecute(stack: LinkedList<Double>, values: Array<Double>): Boolean {
        stack.clear()
        return true
    }
}