package opertator

import java.util.*

class DivisionOperation : Operation(OperatorType.DIVISION) {

    override fun onExecute(stack: LinkedList<Double>, values: Array<Double>) {
        stack.addLast(values[0] / values[1])
    }
}