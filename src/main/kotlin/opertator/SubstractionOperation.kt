package opertator

import java.util.*

class SubstractionOperation : Operation(OperatorType.SUBTRACTION) {

    override fun onExecute(stack: LinkedList<Double>, values: Array<Double>) {
        stack.addLast(values[0] - values[1])
    }
}