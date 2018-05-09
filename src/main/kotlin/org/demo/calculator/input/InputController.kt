package org.demo.calculator.input

import org.demo.calculator.opertator.*
import java.util.*

/**
 * Class which is used to determine and executing the content of the command line input. First this class splitting
 * the content using the blank space between the characters. After that the class checks each character and determines
 * it's operation. Numbers will be saved inside a [LinkedList] object for eventual later usage. Operators (like '+' or
 * '-') will be executed and saved inside a separate [LinkedList] to revert it's action. At the end the class is
 * returning a string [Array] which includes it's response to the user input.
 */
class InputController {

    /**
     * [LinkedList] which saves all numbers from the command line and resulting from operations. Elements will be insert
     * as last-in-last-out (LILO).
     */
    private val stack: LinkedList<Double> = LinkedList()

    /**
     * [LinkedList] which saves all past operations so that those can be reverted if needed. Elements will be insert as
     * last-in-last-out (LILO).
     */
    private val operatorStack: LinkedList<Operation> = LinkedList()

    /**
     * Function which is used to determine the user input from the command line. The user input will be split using the
     * blank between the characters. Each character will then be checked against the different operations. If no
     * operations have been found, the function will check if the given input character is numeric and therefore should
     * be added to the stack. After determine the [Operation], the function executes the found [Operation] object. At
     * the end the object will be saved and the current stack is returned.
     *
     * In case of an input error, the function will return a error message and the current stack. An input error could
     * be an unsupported character or wrongly placed operators inside the command line.
     *
     * @return [Array] which represents the answer of the InputController to the given input string. The answer always
     * includes the current stack. In cases of errors in the given input, the answer will also include a error message.
     * @see OperatorType
     * @see Operation
     */
    fun executeInput(input: String): Array<String> {
        /**
         * Splits the given input using the blank space between the characters.
         */
        val inputArray: List<String> = input.split(Utils.STACK_SEPARATOR)

        /**
         * Iterates though the given input from command line.
         */
        inputArray.withIndex().forEach {
            val operation: Operation

            /**
             * Determines the correct operation type.
             */
            when (it.value) {
                OperatorType.ADDITION.value -> operation = AdditionOperation()
                OperatorType.SUBTRACTION.value -> operation = SubstractionOperation()
                OperatorType.MULTIPLICATION.value -> operation = MultiplicationOperation()
                OperatorType.DIVISION.value -> operation = DivisionOperation()
                OperatorType.SQUARE.value -> operation = SquareOperation()
                OperatorType.UNDO.value -> operation = UndoOperation(operatorStack)
                OperatorType.CLEAR.value -> operation = ClearOperation()
                else -> {
                    /**
                     * If no valid operation have been, checks if the given input character is numeric.
                     */
                    if (Utils.isNumeric(it.value)) {
                        operation = StackOperation(it.value)
                    } else {
                        /**
                         * If the given input not even numeric, the function will return an error message and the
                         * current stack.
                         */
                        return logWithErrorAndStack(it.value, it.index)
                    }
                }
            }

            val successful = operation.execute(stack)

            /**
            * If the execution of the operation was not successful, the function will return an error message and the
            * current stack.
            */
            if (!successful) return logWithErrorAndStack(it.value, it.index)

            /**
            * If the operation was successful, the function will execute the save-function of this operation so that
            * it could be reverted at a later time.
            */
            else operation.save(operatorStack)

        }

        /**
         * Returns an answer which includes the current stack only.
         */
        return logWithStackOnly()
    }

    /**
     *
     */
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