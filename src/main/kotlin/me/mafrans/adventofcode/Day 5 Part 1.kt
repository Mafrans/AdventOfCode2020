package me.mafrans.adventofcode

import kotlin.math.ceil

fun main() {
    val input = Util.readResourceLines("day5.txt")

    // Start timer once the input has been read, so that it only measures the algorithm itself
    Timer.start()

    var highest = 0;

    for (line in input)  {
        var rowMin = 0
        var rowMax = 127
        for (char in line.substring(0, 7)) {
            when(char) {
                'F' -> rowMax -= ceil((rowMax - rowMin) / 2.0).toInt()
                'B' -> rowMin += ceil((rowMax - rowMin) / 2.0).toInt()
            }
        }

        var seatMin = 0
        var seatMax = 7
        for (char in line.substring(7, 10)) {
            when(char) {
                'L' -> seatMax -= ceil((seatMax - seatMin) / 2.0).toInt()
                'R' -> seatMin += ceil((seatMax - seatMin) / 2.0).toInt()
            }
        }

        val id = rowMax * 8 + seatMax
        if (id > highest)
            highest = id
    }

    println(highest)

    // Measure time required to run the algorithm
    println("${Timer.next(Timer.TimeType.MILLIS)}ms")
}