package opertator

enum class OperatorType(val value: String, val requiredValueCount: Int, val revertable: Boolean) {
    ADDITION("+", 2, true),
    SUBTRACTION("-", 2, true),
    MULTIPLICATION("*", 2, true),
    DIVISION("/", 2, true),
    SQUARE("sqrt", 1, true),
    CLEAR("clear", 0, false),
    UNDO("undo", 0, false),
    STACK("stack", 0, true)
}