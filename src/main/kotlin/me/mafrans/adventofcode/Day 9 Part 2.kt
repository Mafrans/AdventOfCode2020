package me.mafrans.adventofcode

import java.lang.Integer.min
import java.util.*
import kotlin.math.max

fun main() {

    val input = Util.readResourceLines("day9.txt")

    // Start timer once the input has been read, so that it only measures the algorithm itself
    Timer.start()

    /*
    *   ========================================
    *   MOST OF THIS CODE IS IDENTICAL TO PART 1
    *   ========================================
    *   The only difference is that instead of printing the invalid number from part 1, it is
    *   stored in a variable called `invalidNumber`
    * */

    /*
    *   Buffer including the last 25 numbers.
    *   It's a linked list because we will only ever be modifying the first and the last value
    * */
    val buffer = LinkedList<Int>()

    // Create the preamble, i.e the first 25 numbers of the input
    for (i in 0 until 25) {
        buffer.push(input[i].toInt())
    }

    var invalidNumber = -1

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

        // If the sum is not equal to the input number, save it and stop looping
        if (!valid) {
            invalidNumber = num;
            break@outer
        }

        // Remove a number from the end of the buffer and add the current number to the beginning of it
        buffer.removeLast()
        buffer.push(num)
    }




    /*
    *   ========================================
    *   PART 2 CODE STARTS HERE
    *   ========================================
    * */

    // Loop over every number in the input
    outer@for (i in input.indices) {
        // Reset the sum, the smallest number and the largest number in the congruent set
        var sum = 0
        var smallest = Integer.MAX_VALUE
        var largest = Integer.MIN_VALUE

        // Loop over every following number, starting at `i` and continuing until stopped or there aren't enough numbers anymore
        for (j in i until input.size) {
            val num = input[j].toInt()

            // Increase the sum by the iterated number
            sum += num
            // If the iterated number is smaller than the saved smallest number, update the saved smallest number
            smallest = min(smallest, num)
            // If the iterated number is larger than the saved largest number, update the saved largest number
            largest = max(largest, num)

            // If the sum is equal to the invalid number, we've found our set
            if (sum == invalidNumber) {
                // In that case, just print the sum of the smallest and largest numbers, then stop the algorithm
                println(smallest + largest)
                break@outer
            }
            // If the sum is greater than the invalid number, reset it and move to the next set
            else if(sum > invalidNumber) {
                break
            }
        }
    }

    // Measure time required to run the algorithm
    println("${Timer.next(Timer.TimeType.MILLIS)}ms")
}