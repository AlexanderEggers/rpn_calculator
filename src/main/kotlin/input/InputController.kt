package input

import opertator.*
import java.util.*

class InputController {

    private val stack: LinkedList<Double> = LinkedList()
    private val operatorStack: LinkedList<Operation> = LinkedList()

    fun executeInput(input: String): Array<String> {
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
                stringBuilder.append(Utils.STACK_SEPERATOR)
            }

            val it = iterator.next()
            stringBuilder.append(Utils.formatDouble(it))
        }

        val result = stringBuilder.toString()
        return if(!extra.isEmpty()) {
            arrayOf(extra, result)
        } else {
            arrayOf(result)
        }
    }
}