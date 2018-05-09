package org.demo.calculator.input

import org.demo.calculator.opertator.*
import java.util.*

/**
 * Class which is used to determine and executing the content of the command line input. First this class splitting
 * the content using the blank space between the characters. After that the class checks each character and determines
 * it's operation. Numbers will be saved inside a LinkedList object for eventual later usage. Operators (like '+' or
 * '-') will be executed and saved inside a separate LinkedList to revert it's action. At the end the class is
 * returning a string array which includes it's response to the user input.
 *
 * @see InputController.executeInput Main function of this object.
 */
class InputController {

    /**
     * LinkedList which saves all numbers from the command line and resulting from operations. Content will be insert
     * as last-in-last-out (LILO).
     */
    private val stack: LinkedList<Double> = LinkedList()

    /**
     * LinkedList which saves all past operations so that those can be reverted if needed. Content will be insert as
     * last-in-last-out (LILO).
     */
    private val operatorStack: LinkedList<Operation> = LinkedList()

    fun executeInput(input: String): Array<String> {
        val inputArray: List<String> = input.split(Utils.STACK_SEPARATOR)

        inputArray.withIndex().forEach {
            val operation: Operation

            when (it.value) {
                OperatorType.ADDITION.value -> operation = AdditionOperation()
                OperatorType.SUBTRACTION.value -> operation = SubstractionOperation()
                OperatorType.MULTIPLICATION.value -> operation = MultiplicationOperation()
                OperatorType.DIVISION.value -> operation = DivisionOperation()
                OperatorType.SQUARE.value -> operation = SquareOperation()
                OperatorType.UNDO.value -> operation = UndoOperation(operatorStack)
                OperatorType.CLEAR.value -> operation = ClearOperation()
                else -> {
                    if (Utils.isNumeric(it.value)) {
                        operation = StackOperation(it.value)
                    } else {
                        return logWithErrorAndStack(it.value, it.index)
                    }
                }
            }

            val successful = operation.execute(stack)
            if (!successful) return logWithErrorAndStack(it.value, it.index)
            else operation.save(operatorStack)
        }

        return logWithStackOnly()
    }

    private fun logWithErrorAndStack(inputValue: String, pos: Int): Array<String> {
        return prepareStackForOutput("operator $inputValue (position: ${pos * 2 + 1}): insufficient parameters")
    }

    private fun logWithStackOnly(): Array<String> {
        return prepareStackForOutput()
    }

    private fun prepareStackForOutput(extra: String = ""): Array<String> {
        val stringBuilder = StringBuilder()
        stringBuilder.append("stack:")

        val iterator = stack.iterator()
        while (iterator.hasNext()) {
            if (iterator.hasNext()) {
                stringBuilder.append(Utils.STACK_SEPARATOR)
            }

            val it = iterator.next()
            stringBuilder.append(Utils.formatDouble(it))
        }

        val result = stringBuilder.toString()
        return if (!extra.isEmpty()) {
            arrayOf(extra, result)
        } else {
            arrayOf(result)
        }
    }
}