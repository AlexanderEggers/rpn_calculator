import input.InputController
import org.junit.Assert.assertEquals
import org.junit.Test

class UnitTests {

    private val inputController: InputController = InputController()

    @Test
    fun example1() {
        val result = inputController.executeInput("5 2")
        assertEquals("stack: 5 2", result[0])
    }

    @Test
    fun example2() {
        var result = inputController.executeInput("2 sqrt")
        assertEquals("stack: 1.4142135623", result[0])

        result = inputController.executeInput("clear 9 sqrt")
        assertEquals("stack: 3", result[0])
    }

    @Test
    fun example3() {
        var result = inputController.executeInput("5 2 -")
        assertEquals("stack: 3", result[0])

        result = inputController.executeInput("3 -")
        assertEquals("stack: 0", result[0])

        result = inputController.executeInput("clear")
        assertEquals("stack:", result[0])
    }

    @Test
    fun example4() {
        var result = inputController.executeInput("5 4 3 2")
        assertEquals("stack: 5 4 3 2", result[0])

        result = inputController.executeInput("undo undo *")
        assertEquals("stack: 20", result[0])

        result = inputController.executeInput("5 *")
        assertEquals("stack: 100", result[0])

        result = inputController.executeInput("undo")
        assertEquals("stack: 20 5", result[0])
    }

    @Test
    fun example5() {
        var result = inputController.executeInput("7 12 2 /")
        assertEquals("stack: 7 6", result[0])

        result = inputController.executeInput("*")
        assertEquals("stack: 42", result[0])

        result = inputController.executeInput("4 /")
        assertEquals("stack: 10.5", result[0])
    }

    @Test
    fun example6() {
        var result = inputController.executeInput("1 2 3 4 5")
        assertEquals("stack: 1 2 3 4 5", result[0])

        result = inputController.executeInput("*")
        assertEquals("stack: 1 2 3 20", result[0])

        result = inputController.executeInput("clear 3 4 -")
        assertEquals("stack: -1", result[0])
    }

    @Test
    fun example7() {
        var result = inputController.executeInput("1 2 3 4 5")
        assertEquals("stack: 1 2 3 4 5", result[0])

        result = inputController.executeInput("* * * *")
        assertEquals("stack: 120", result[0])
    }

    @Test
    fun example8() {
        val result = inputController.executeInput("1 2 3 * 5 + * * 6 5")
        assertEquals("operator * (position: 15): insufficient parameters", result[0])
        assertEquals("stack: 11", result[1])
    }

    @Test
    fun errorInputTest() {
        val result = inputController.executeInput("1 2 3 * noNumber")
        assertEquals("operator noNumber (position: 9): insufficient parameters", result[0])
        assertEquals("stack: 1 6", result[1])
    }
}