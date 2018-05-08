package logic

import java.util.*

class InputController {

    private val stack: LinkedList<String> = LinkedList()
    private val operatorStack: LinkedList<OperatorElement> = LinkedList()
    private val operatorBackend: OperatorBackend = OperatorBackend()

    fun executeInput(input: String): String {
        val inputArray: List<String> = input.split(" ")

        inputArray.withIndex().forEach {
            val operator = Utils.stringToOperator(it.value)
            if (operator != Operator.STACK) {
                if (operator == Operator.CLEAR) {
                    stack.clear()
                } else if (operator == Operator.UNDO) {
                    if(!operatorStack.isEmpty()) {
                        stack.pollLast()
                        val operatorElement = operatorStack.pollLast()
                        if(operatorElement.operator == Operator.STACK) {
                            stack.pollLast()
                        } else {
                            if(operatorElement.value2 != null) {
                                stack.addLast(operatorElement.value2)
                            }
                            stack.addLast(operatorElement.value1)
                        }
                    }
                } else if (operator == Operator.SQUARE) {
                    if (stack.size >= 1) {
                        val value1 = Utils.parseToDouble(stack.pollLast())
                        val result = operatorBackend.calculateOneValueOperations(value1, operator)
                        stack.addLast(result)
                        operatorStack.addLast(OperatorElement(operator, Utils.formatDouble(value1)))
                    } else {
                        return "operator ${operator.value} (position: ${it.index * 2 + 1}): insufficient parameters"
                    }
                } else if (stack.size >= 2) {
                    val value2 = Utils.parseToDouble(stack.pollLast())
                    val value1 = Utils.parseToDouble(stack.pollLast())

                    val result = operatorBackend.calculateTwoValueOperations(value1, value2, operator)
                    stack.addLast(result)
                    operatorStack.addLast(OperatorElement(operator, Utils.formatDouble(value1), Utils.formatDouble(value2)))
                } else {
                    return "operator ${operator.value} (position: ${it.index * 2 + 1}): insufficient parameters"
                }
            } else if (Utils.isNumeric(it.value)) {
                stack.addLast(it.value)
                operatorStack.addLast(OperatorElement(operator, it.value))
            } else {
                return "value '${it.value}' (position: ${it.index * 2 + 1}): unsupported character"
            }
        }

        val stringBuilder = StringBuilder()
        val iterator = stack.iterator()

        while (iterator.hasNext()) {
            val it = iterator.next()
            stringBuilder.append(it)

            if (iterator.hasNext()) {
                stringBuilder.append(" ")
            }
        }

        return stringBuilder.toString()
    }
}