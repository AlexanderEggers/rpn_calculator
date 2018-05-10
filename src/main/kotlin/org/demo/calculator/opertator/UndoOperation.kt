package org.demo.calculator.opertator

import java.util.*

/**
 * Operation which reverts the latest operation's action. This operation type cannot be reverted and requires no
 * elements from the stack.
 */
class UndoOperation
constructor(private val operationStack: LinkedList<Operation>) : Operation(OperationType.UNDO) {

    override fun onExecute(stack: LinkedList<Double>, values: Array<Double>): Boolean {
        /**
         * Retrieves the latest operation from the operation stack and calls the revert function on that object.
         */
        operationStack.pollLast().revert(stack)
        return true
    }
}