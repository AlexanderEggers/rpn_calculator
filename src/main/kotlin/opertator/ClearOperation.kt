package opertator

import java.util.*

class ClearOperation : Operation(OperatorType.CLEAR) {

    override fun onExecute(stack: LinkedList<Double>, values: Array<Double>) {
        stack.clear()
    }
}