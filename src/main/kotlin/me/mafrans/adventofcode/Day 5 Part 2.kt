package me.mafrans.adventofcode

import kotlin.math.ceil

fun main() {
    val input = Util.readResourceLines("day5.txt")

    // Start timer once the input has been read, so that it only measures the algorithm itself
    Timer.start()

    // A hashset containing all ids included in the input
    val ids = HashSet<Int>()

    // Generate all ids available in the input, and add them to the hashset
    for (line in input)  {
        val rowData = line.substring(0, 7)
        val seatData = line.substring(7, 10)

        // If the row is all the way in the front or all the way to the back, ignore it
        if (rowData == "FFFFFFF" || rowData == "BBBBBBB")
            continue

        /*
        *   Calculate the row by procedurally reducing the maximum and increasing the minimum
        *   If 'F', decrease the maximum by half the difference between maximum and minimum (rounding up)
        *   If 'B', increase the minimum by half the difference between maximum and minimum (rounding up)
        * */
        var rowMin = 0
        var rowMax = 127
        for (char in rowData) {
            when(char) {
                'F' -> rowMax -= ceil((rowMax - rowMin) / 2.0).toInt()
                'B' -> rowMin += ceil((rowMax - rowMin) / 2.0).toInt()
            }
        }

        /*
        *   Calculate the seat by procedurally reducing the maximum and increasing the minimum
        *   If 'L', decrease the maximum by half the difference between maximum and minimum (rounding up)
        *   If 'R', increase the minimum by half the difference between maximum and minimum (rounding up)
        * */
        var seatMin = 0
        var seatMax = 7
        for (char in seatData) {
            when(char) {
                'L' -> seatMax -= ceil((seatMax - seatMin) / 2.0).toInt()
                'R' -> seatMin += ceil((seatMax - seatMin) / 2.0).toInt()
            }
        }

        // Add the id hash to the ´ids´ list
        ids += rowMax * 8 + seatMax
    }

    // Loop over every possible row, ignoring the frontmost row and backmost row
    outer@for (row in 1..126) {
        // Loop over every possible seat, ignoring the leftmost seat and the rightmost seat
        for (seat in 1..7) {
            // Calculate the seat id
            val id = row * 8 + seat

            // If the id is not in our input but the adjacent ids are, we've found the right seat
            if (!ids.contains(id) && ids.contains(id + 1) && ids.contains(id - 1)) {
                println(id)
                break@outer
            }
        }
    }

    // Measure time required to run the algorithm
    println("${Timer.next(Timer.TimeType.MILLIS)}ms")
}