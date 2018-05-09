package input

import opertator.*
import java.util.*

class InputController {

    private val stack: LinkedList<Double> = LinkedList()
    private val operatorStack: LinkedList<Operation> = LinkedList()

    fun executeInput(input: String): String {
        val inputArray: List<String> = input.split(" ")

        inputArray.withIndex().forEach {
            val operation: Operation

            when (it.value) {
                "+" -> operation = AdditionOperation()
                "-" -> operation = SubstractionOperation()
                "*" -> operation = MultiplicationOperation()
                "/" -> operation = DivisionOperation()
                "sqrt" -> operation = SquareOperation()
                "undo" -> operation = UndoOperation(operatorStack)
                "clear" -> operation = ClearOperation()
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
        return prepareStackForOutput("value $inputValue (position: ${pos * 2 + 1}): insufficient parameters")
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
                stringBuilder.append(" ")
            }
        }

        return stringBuilder.toString()
    }
}