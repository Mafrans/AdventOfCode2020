package me.mafrans.adventofcode

import java.util.*

fun main() {

    val input = Util.readResourceLines("day9.txt")

    // Start timer once the input has been read, so that it only measures the algorithm itself
    Timer.start()

    /*
    *   Buffer including the last 25 numbers.
    *   It's a linked list because we will only ever be modifying the first and the last value
    * */
    val buffer = LinkedList<Int>()

    // Create the preamble, i.e the first 25 numbers of the input
    for (i in 0 until 25) {
        buffer.push(input[i].toInt())
    }

    // Loop over the next numbers in the input
    outer@for (i in 25 until input.size) {
        val num = input[i].toInt()

        // Loop over the buffer twice to find occurrences where the sum is equal to the input number
        var valid = false
        b1@for (b1 in buffer) {
            for (b2 in buffer) {
                // If the sum is equal to the input number (and both numbers are different), set the number to be valid and stop checking for sums
                if (b1 + b2 == num && b1 != b2) {
                    valid = true
                    break@b1
                }
            }
        }

        // If the sum is not equal to the input number, print it and stop the loops
        if (!valid) {
            println(num)
            break@outer
        }

        // Remove a number from the end of the buffer and add the current number to the beginning of it
        buffer.removeLast()
        buffer.push(num)
    }

    // Measure time required to run the algorithm
    println("${Timer.next(Timer.TimeType.MILLIS)}ms")
}