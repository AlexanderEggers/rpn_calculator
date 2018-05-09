import input.InputController
import java.util.*

open class CalculatorApp {

    private val scanner: Scanner = Scanner(System.`in`)
    private val inputController: InputController = InputController()

    fun run() {
        while (true) {
            val input = scanner.nextLine()
            inputController.executeInput(input).forEach {
                println(it)
            }
        }
    }
}

fun main(args: Array<String>) {
    CalculatorApp().run()
}