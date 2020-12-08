package me.mafrans.adventofcode

fun main() {

    val input = Util.readResourceLines("day8.txt")

    // Start timer once the input has been read, so that it only measures the algorithm itself
    Timer.start()

    // Accumulator global for the program
    var accumulator = 0
    // Index, i.e which operation is currently running
    var index = 0

    // Array containing all input operations, what they do, as well as if they've been run before
    val code = input.map {
        /*
        *   Operation strings follow the format `<operation> <sign><amount>`
        *
        *   Because operation names are always 3 letters, we can use String.substring()
        *   to figure out which value is which.
        * */
        val operation = it.substring(0, 3)
        val sign = it[4]
        val amount = when(sign) {
            '+' -> it.substring(5).toInt()
            '-' -> it.substring(5).toInt() * -1
            else -> 0
        }

        // A triple is a collection of exactly three items, in this case the operation type, its value and whether it has been run before
        Triple(operation, amount, false)
    }.toTypedArray()

    // Loop until either broken or index reaches the end of the program
    while (index < code.size) {
        // Destructure the triple from `code[index]` into its individual values
        val (operation, amount, used) = code[index]

        // If the operation has already been run, stop the loop and end the program
        if (used) break

        // Update the array to indicate that the operation has been run
        code[index] = Triple(operation, amount, true)

        // Run the operation
        when(operation) {
            // "acc" increases the accumulator by `amount`
            "acc" -> accumulator += amount
            // "jmp" increases the index by `amount`, but since index is automatically increased by 1 each operation, we subtract 1
            "jmp" -> {
                index += amount - 1

                if(index < 0) {
                    break;
                }
            }
            // "nop" does nothing
            "nop" -> {}
        }

        index++
    }

    println(accumulator)

    // Measure time required to run the algorithm
    println("${Timer.next(Timer.TimeType.MILLIS)}ms")
}