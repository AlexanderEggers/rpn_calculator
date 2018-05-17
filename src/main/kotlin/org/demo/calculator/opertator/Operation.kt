package org.demo.calculator.opertator

import org.demo.calculator.input.InputController
import java.util.*
import kotlin.collections.ArrayList

/**
 * Abstract class which is used to define an operation that can be executed on the current stack content. Operations
 * can insert new values to the stack or simply remove certain element from it. Each operation is based on an
 * [OperationType]. This object defines the required amount of elements inside the stack to be able to fulfil the
 * operation and if the operation has the possible to be reverted.
 *
 * @param operationType [OperationType] that defines this operation.
 */
abstract class Operation constructor(private val operationType: OperationType) {

    /**
     * Saves all consumed values of this operation inside a separate list. This list will be used to reverted this
     * operation if needed.
     */
    private val valueList: ArrayList<Double> = ArrayList()

    /**
     * Returns if the stack has enough elements to fulfill this operation.
     *
     * @param stack Stack which has stored all numbers from the command line
     * @return True if the stack has enough elements to fulfill the operation, false otherwise.
     */
    private fun hasStackEnoughElements(stack: LinkedList<Double>): Boolean {
        return stack.size >= operationType.requiredStackElements
    }

    /**
     * Polls values from the given stack and adds it to the separate [Operation.valueList]. The function will only
     * retrieve the defined amount in the [OperationType] object.
     *
     * @param stack Stack which has stored all numbers from the command line
     */
    private fun prepareExecuteValues(stack: LinkedList<Double>) {
        for (i in 1..operationType.requiredStackElements) {
            valueList.add(stack.pollLast())
        }
    }

    /**
     * Abstract function which defines the main-body for this operation.
     *
     * @param stack Stack which has stored all numbers from the command line
     * @param values Optional [Array] which includes value the operation can work with. This array is empty if the
     * operation requires no stack elements to work with.
     * @return True if the operation was successful, false otherwise
     */
    protected abstract fun onExecute(stack: LinkedList<Double>, values: Array<Double>): Boolean

    /**
     * Function which prepares the execution of the operation by checking the available elements inside the stack and
     * adds those elements to an [Array] the custom operation can use.
     *
     * @param stack Stack which has stored all numbers from the command line
     * @return True if the operation was successful, false otherwise
     */
    fun execute(stack: LinkedList<Double>): Boolean {
        /**
         * Makes sure the current stack has enough elements for the operation.
         */
        if (hasStackEnoughElements(stack)) {
            /**
             * Only prepares stack elements for the [Operation.valueList] if the operation is actually needing elements
             * to fulfill it's job.
             */
            if (operationType.requiredStackElements > 0) prepareExecuteValues(stack)

            /**
             * Converts the [Operation.valueList] as a reversed list to an [Array] that the operation can use. The
             * reversed state ensures, that the first value of the array is the first value that should be operated on,
             * like a - b and not b - a. The return value of this function will be used to tell the [InputController]
             * if the operation was successful.
             *
             * Example with: 20 5 *
             * - Inside [Operation.valueList] the values are saved as 5 20 because the class gets the values as
             * pollLast.
             * - Therefore the onExecute function should retrieve the values as 20 5 because 20 is out A and 5 our B.
             */
            return onExecute(stack, valueList.toTypedArray())
        }
        return false
    }

    /**
     * Reverts the operation by using the values inside [Operation.valueList].
     *
     * @param stack Stack which has stored all numbers from the command line
     */
    fun revert(stack: LinkedList<Double>) {
        /**
         * Checks if the operation is defined as revertable.
         */
        if (operationType.revertable) {
            /**
             * Removes the latest element that have been added to the stack from this operation. The reversed state
             * ensures that the right order of elements are added back to the stack.
             */
            stack.pollLast()
            valueList.reversed().forEach {
                stack.addLast(it)
            }
        }
    }

    /**
     * Stores the operation object inside a [LinkedList] to enable the possibility to revert it's changes to the stack.
     *
     * @param operationStack [LinkedList] that stores all past operations
     */
    fun save(operationStack: LinkedList<Operation>) {
        if (operationType.revertable) {
            operationStack.addLast(this)
        }
    }
}