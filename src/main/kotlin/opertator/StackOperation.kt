package opertator

import input.Utils
import java.util.*

class StackOperation(private val value: String) : Operation(OperatorType.STACK) {

    override fun onExecute(stack: LinkedList<Double>, values: Array<Double>) {
        stack.addLast(Utils.parseToDouble(value))
    }
}