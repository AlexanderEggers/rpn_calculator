package opertator

import java.util.*
import kotlin.collections.ArrayList

abstract class Operation constructor(private val operatorType: OperatorType) {

    private val valueList: ArrayList<Double> = ArrayList()

    private fun hasStackEnoughElements(stack: LinkedList<Double>): Boolean {
        return stack.size >= operatorType.requiredStackElements
    }

    private fun prepareExecuteValues(stack: LinkedList<Double>) {
        for (i in 1..operatorType.requiredStackElements) {
            valueList.add(stack.pollLast())
        }
    }

    fun execute(stack: LinkedList<Double>): Boolean {
        if (hasStackEnoughElements(stack)) {
            if (operatorType.requiredStackElements > 0) prepareExecuteValues(stack)
            onExecute(stack, valueList.reversed().toTypedArray())
            return true
        }
        return false
    }

    abstract fun onExecute(stack: LinkedList<Double>, values: Array<Double>)

    fun revert(stack: LinkedList<Double>) {
        if (operatorType.revertable) {
            stack.pollLast()
            valueList.reversed().forEach {
                stack.addLast(it)
            }
        }
    }

    fun save(operationStack: LinkedList<Operation>) {
        if (operatorType.revertable) {
            operationStack.addLast(this)
        }
    }
}