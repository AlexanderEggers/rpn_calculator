package org.demo.calculator.input

import org.demo.calculator.input.Utils.Companion.STACK_SEPARATOR
import org.demo.calculator.input.Utils.Companion.formatDouble
import org.demo.calculator.opertator.*
import java.util.*

/**
 * Class which is used to determine and executing the content of the command line input. First this class splitting
 * the content using the blanks between the characters. After that the class checks each character and determines
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
     * blanks between the characters. Each character will then be checked against the different operations. If no
     * operations have been found, the function will check if the given input character is numeric and therefore should
     * be added to the stack. After determine the [Operation], the function executes the found [Operation] object. At
     * the end the object will be saved and the current stack is returned.
     *
     * In case of an input error, the function will return a error message and the current stack. An input error could
     * be an unsupported character or wrongly placed operators inside the command line.
     *
     * @return [Array] which represents the answer of the InputController to the given input string. The answer always
     * includes the current stack. In cases of errors in the given input, the answer will also include a error message.
     * @see OperationType
     * @see Operation
     */
    fun executeInput(input: String): Array<String> {
        /**
         * Splits the given input using the blanks between the characters.
         */
        val inputArray: List<String> = input.split(STACK_SEPARATOR)

        /**
         * Iterates though the given input from command line.
         */
        inputArray.withIndex().forEach {
            /**
             * Determines the correct operation type.
             */
            val operation: Operation = when (it.value) {
                OperationType.ADDITION.key -> AdditionOperation()
                OperationType.SUBTRACTION.key -> SubtractionOperation()
                OperationType.MULTIPLICATION.key -> MultiplicationOperation()
                OperationType.DIVISION.key -> DivisionOperation()
                OperationType.SQUARE.key -> SquareOperation()
                OperationType.UNDO.key -> UndoOperation(operatorStack)
                OperationType.CLEAR.key -> ClearOperation()
                else -> {
                    StackOperation(it.value)
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
     * Initializing the InputController answer using an error message and the current stack. The error message uses the
     * given parameters to give the user a understandable explanation for the error.
     *
     * @param inputValue input substring which has caused this error
     * @param pos position inside the iterable object which has splitted the given input using the blanks between
     * characters
     * @return [Array] which holds the answer for the given command line input
     */
    private fun logWithErrorAndStack(inputValue: String, pos: Int): Array<String> {
        return arrayOf("operator $inputValue (position: ${pos * 2 + 1}): insufficient parameters", prepareStackForOutput())
    }

    /**
     * Initializing the InputController answer using the current stack.
     *
     * @return [Array] which holds the answer for the given command line input
     */
    private fun logWithStackOnly(): Array<String> {
        return arrayOf(prepareStackForOutput())
    }

    /**
     * Initializing the current stack content for the InputController answer. The function is using the [StringBuilder]
     * to append the different values of the stack. At the beginning, the [StringBuilder] is placing a specific
     * substring so that the user can easier recognize the part of the answer which displays the current stack content.
     * Between each value, the [StringBuilder] is placing a blank.
     *
     * @return current stack as one single [String] object
     */
    private fun prepareStackForOutput(): String {
        val stringBuilder = StringBuilder()

        /**
         * Adds this substring so that the user can easier recognize this answer.
         */
        stringBuilder.append("stack:")

        val iterator = stack.iterator()
        while (iterator.hasNext()) {
            /**
             * Adds a blank before each stack element.
             */
            stringBuilder.append(STACK_SEPARATOR)

            /**
             * Format each stack value so that it would only show up to 10 positions.
             */
            stringBuilder.append(formatDouble(iterator.next()))
        }

        return stringBuilder.toString()
    }
}