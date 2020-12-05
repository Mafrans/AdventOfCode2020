package me.mafrans.adventofcode

import kotlin.math.max

/*
*   Day 5 Part 1, Version 2
*   ~ 50-60% faster than version 1
* */
fun main() {
    val input = Util.readResourceLines("day5.txt")

    // Start timer once the input has been read, so that it only measures the algorithm itself
    Timer.start()

    println(
        // Use reduce to reduce the list of input seats to a single seat id
        (input as List<Any>).reduce { acc, line ->
            // If the iterated seat has a higher id than the one in storage, store it instead
            max(
                // If the stored id isn't an int, use 0, otherwise use it
                if(acc is Int) acc else 0,

                /*
                *   Calculate the seat id by replacing each F or L with a 0
                *   and each B or R with a 1, then parsing it as a binary integer.
                *
                *   This works because limiting a value in the way described by the question is the
                *   same as parsing it as a binary value.
                * */
                (line as String)
                    .replace('F', '0')
                    .replace('L', '0')
                    .replace('B', '1')
                    .replace('R', '1')
                    .toInt(2)
            )
        }
    )

    // Measure time required to run the algorithm
    println("${Timer.next(Timer.TimeType.MILLIS)}ms")
}