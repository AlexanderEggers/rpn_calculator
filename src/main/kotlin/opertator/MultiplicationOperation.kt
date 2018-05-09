package opertator

import java.util.*

class MultiplicationOperation : Operation(OperatorType.MULTIPLICATION) {

    override fun onExecute(stack: LinkedList<Double>, values: Array<Double>) {
        stack.addLast(values[0] * values[1])
    }
}