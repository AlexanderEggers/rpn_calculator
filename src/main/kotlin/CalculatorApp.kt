import logic.InputController
import java.util.*

class CalculatorApp {

    private val scanner: Scanner = Scanner(System.`in`)
    private val inputController: InputController = InputController()

    fun run() {
        while (true) {
            val input = scanner.nextLine()
            val currentStack = inputController.executeInput(input)
            println("stack: $currentStack")
        }
    }
}