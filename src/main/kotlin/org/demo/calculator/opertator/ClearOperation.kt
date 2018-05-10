package org.demo.calculator.opertator

import java.util.*

class ClearOperation : Operation(OperationType.CLEAR) {

    override fun onExecute(stack: LinkedList<Double>, values: Array<Double>): Boolean {
        stack.clear()
        return true
    }
}