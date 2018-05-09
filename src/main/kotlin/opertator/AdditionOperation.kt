package opertator

import java.util.*

class AdditionOperation : Operation(OperatorType.ADDITION) {

    override fun onExecute(stack: LinkedList<Double>, values: Array<Double>) {
        stack.addLast(values[0] + values[1])
    }
}