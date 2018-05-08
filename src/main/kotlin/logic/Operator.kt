package logic

enum class Operator(val value: String) {
    ADDITION("+"),
    SUBTRACTION("-"),
    MULTIPLICATION("*"),
    DIVISION("/"),
    SQUARE("sqrt"),
    CLEAR("clear"),
    UNDO("undo"),
    STACK("stack")
}