package org.demo.calculator.opertator

import org.demo.calculator.input.Utils
import java.util.*

class StackOperation(private val value: String) : Operation(OperationType.STACK) {

    override fun onExecute(stack: LinkedList<Double>, values: Array<Double>): Boolean {
        val doubleValue = Utils.parseToDouble(value)
        if (doubleValue != null) {
            stack.addLast(doubleValue)
            return true
        }
        return false
    }
}