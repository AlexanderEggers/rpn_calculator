package org.demo.calculator.opertator

/**
 * Enum which defines the different types of operations that are supported. Each operation is defined by a key, the
 * required amount of stack elements for this type and if it is revertable. by using the 'undo' command parameter.
 *
 * @param key String which is used to identify the needed operation type
 * @param requiredStackElements Required amount of stack elements needed for this operation type
 * @param revertable True if this operation type revertable, false otherwise
 */
enum class OperationType(val key: String, val requiredStackElements: Int, val revertable: Boolean) {
    /**
     * Default addition operation which requires two elements from the stack and can be reverted.
     */
    ADDITION("+", 2, true),

    /**
     * Default subtraction operation which requires two elements from the stack and can be reverted.
     */
    SUBTRACTION("-", 2, true),

    /**
     * Default multiplication operation which requires two elements from the stack and can be reverted.
     */
    MULTIPLICATION("*", 2, true),

    /**
     * Default division operation which requires two elements from the stack and can be reverted.
     */
    DIVISION("/", 2, true),

    /**
     * Default square operation which requires one element from the stack and can be reverted.
     */
    SQUARE("sqrt", 1, true),

    /**
     * Operation which will be used to remove all elements from the current stack. This operation type cannot be
     * reverted and requires no elements from the stack in order to work.
     */
    CLEAR("clear", 0, false),

    /**
     * Operation which reverts the latest operation's action. This operation type cannot be reverted and requires no
     * elements from the stack.
     */
    UNDO("undo", 0, false),

    /**
     * Operation which is used to insert new elements to the stack. This operation type requires no elements from the
     * stack in order to work but can be reverted if needed.
     */
    STACK("stack", 0, true)
}