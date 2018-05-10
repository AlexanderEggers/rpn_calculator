package org.demo.calculator.opertator

import java.util.*

class SquareOperation : Operation(OperationType.SQUARE) {

    override fun onExecute(stack: LinkedList<Double>, values: Array<Double>): Boolean {
        stack.addLast(Math.sqrt(values[0]))
        return true
    }
}