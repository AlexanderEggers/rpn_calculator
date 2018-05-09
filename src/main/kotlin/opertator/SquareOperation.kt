package opertator

import java.util.*

class SquareOperation : Operation(OperatorType.SQUARE) {

    override fun onExecute(stack: LinkedList<Double>, values: Array<Double>) {
        stack.addLast(Math.sqrt(values[0]))
    }
}