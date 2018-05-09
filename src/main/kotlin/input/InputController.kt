package input

import opertator.*
import java.util.*

class InputController {

    private val stack: LinkedList<Double> = LinkedList()
    private val operatorStack: LinkedList<Operation> = LinkedList()

    fun executeInput(input: String): String {
        val inputArray: List<String> = input.split(Utils.STACK_SEPERATOR)

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

    private fun logWithErrorAndStack(inputValue: String, pos: Int): String {
        return prepareStackForOutput("operator $inputValue (position: ${pos * 2 + 1}): insufficient parameters")
    }

    private fun logWithStackOnly(): String {
        return prepareStackForOutput()
    }

    private fun prepareStackForOutput(extra: String = ""): String {
        val stringBuilder = StringBuilder()
        val iterator = stack.iterator()

        if (!extra.isEmpty()) stringBuilder.appendln(extra)
        stringBuilder.append("stack: ")

        while (iterator.hasNext()) {
            val it = iterator.next()
            stringBuilder.append(Utils.formatDouble(it))

            if (iterator.hasNext()) {
                stringBuilder.append(Utils.STACK_SEPERATOR)
            }
        }

        return stringBuilder.toString()
    }
}