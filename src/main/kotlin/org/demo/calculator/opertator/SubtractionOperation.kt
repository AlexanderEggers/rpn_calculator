package org.demo.calculator.opertator

import java.util.*

class SubtractionOperation : Operation(OperationType.SUBTRACTION) {

    override fun onExecute(stack: LinkedList<Double>, values: Array<Double>): Boolean {
        stack.addLast(values[0] - values[1])
        return true
    }
}