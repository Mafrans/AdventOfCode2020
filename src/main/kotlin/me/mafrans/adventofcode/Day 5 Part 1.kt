package me.mafrans.adventofcode

import kotlin.math.ceil

fun main() {
    val input = Util.readResourceLines("day5.txt")

    // Start timer once the input has been read, so that it only measures the algorithm itself
    Timer.start()

    // The highest seat id
    var highest = 0;

    for (line in input)  {
        /*
        *   Calculate the row by procedurally reducing the maximum and increasing the minimum
        *   If 'F', decrease the maximum by half the difference between maximum and minimum (rounding up)
        *   If 'B', increase the minimum by half the difference between maximum and minimum (rounding up)
        * */
        var rowMin = 0
        var rowMax = 127
        for (char in line.substring(0, 7)) {
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
        for (char in line.substring(7, 10)) {
            when(char) {
                'L' -> seatMax -= ceil((seatMax - seatMin) / 2.0).toInt()
                'R' -> seatMin += ceil((seatMax - seatMin) / 2.0).toInt()
            }
        }

        // Calculate the seat id hash
        val id = rowMax * 8 + seatMax

        // If the id hash is higher than the current highest, set the current highest to the id hash
        if (id > highest)
            highest = id
    }

    println(highest)

    // Measure time required to run the algorithm
    println("${Timer.next(Timer.TimeType.MILLIS)}ms")
}