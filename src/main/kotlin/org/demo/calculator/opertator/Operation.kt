package org.demo.calculator.opertator

import java.util.*
import kotlin.collections.ArrayList

/**
 * Abstract class which is used to define an operation that can be executed on the current stack content. Operations
 * can insert new values to the stack or simply remove certain element from it. Each operation is based on an
 * [OperationType]. This object defines the required amount of elements inside the stack to be able to fulfil the
 * operation and if the operation has the possible to be reverted.
 *
 * @param operationType [OperationType] that defines this Operation.
 */
abstract class Operation constructor(private val operationType: OperationType) {

    /**
     *
     */
    private val valueList: ArrayList<Double> = ArrayList()

    private fun hasStackEnoughElements(stack: LinkedList<Double>): Boolean {
        return stack.size >= operationType.requiredStackElements
    }

    private fun prepareExecuteValues(stack: LinkedList<Double>) {
        for (i in 1..operationType.requiredStackElements) {
            valueList.add(stack.pollLast())
        }
    }

    fun execute(stack: LinkedList<Double>): Boolean {
        if (hasStackEnoughElements(stack)) {
            if (operationType.requiredStackElements > 0) prepareExecuteValues(stack)
            return onExecute(stack, valueList.reversed().toTypedArray())
        }
        return false
    }

    abstract fun onExecute(stack: LinkedList<Double>, values: Array<Double>): Boolean

    fun revert(stack: LinkedList<Double>) {
        if (operationType.revertable) {
            stack.pollLast()
            valueList.reversed().forEach {
                stack.addLast(it)
            }
        }
    }

    fun save(operationStack: LinkedList<Operation>) {
        if (operationType.revertable) {
            operationStack.addLast(this)
        }
    }
}