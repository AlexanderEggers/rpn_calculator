package org.demo.calculator

import org.demo.calculator.input.InputController
import java.util.*

/**
 * Class which is runs in a loop to retrieve the latest input from the command line. The input value will then send to
 * the InputController class. This class is also responsible to display any results from the InputController by sending
 * it's strings to the command line.
 *
 * @see InputController
 */
class CalculatorApp {

    /**
     * Scanner object which is used to get the latest user input from the command line.
     */
    private val scanner: Scanner = Scanner(System.`in`)

    /**
     * Object which is used to execute the user content from the command line.
     */
    private val inputController: InputController = InputController()

    /**
     * Function which is running in a loop and waiting for new user input strings. It also sends any results to back to
     * the command line. The result is stored inside an array to provide multiple message per user input (e.g. error
     * message + stack information).
     */
    fun run() {
        while (true) {
            val input = scanner.nextLine()
            inputController.executeInput(input).forEach {
                println(it)
            }
        }
    }
}