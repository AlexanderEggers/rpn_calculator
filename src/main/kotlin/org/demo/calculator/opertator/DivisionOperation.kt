package org.demo.calculator.opertator

import java.util.*

class DivisionOperation : Operation(OperationType.DIVISION) {

    override fun onExecute(stack: LinkedList<Double>, values: Array<Double>): Boolean {
        if (values[1] != 0.0) {
            stack.addLast(values[0] / values[1])
            return true
        }
        return false
    }
}