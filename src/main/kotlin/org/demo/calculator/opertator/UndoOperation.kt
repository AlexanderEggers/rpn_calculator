package org.demo.calculator.opertator

import java.util.*

class UndoOperation
constructor(private val operationStack: LinkedList<Operation>) : Operation(OperatorType.UNDO) {

    override fun onExecute(stack: LinkedList<Double>, values: Array<Double>) {
        operationStack.pollLast().revert(stack)
    }
}